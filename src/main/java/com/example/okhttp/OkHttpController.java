package com.example.okhttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.example.okhttp.retrofit.MyLoggingInterceptor;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 朱伟伟
 * @date 2020-12-17 20:11:50
 * @description
 */
@RestController
@RequestMapping("/okHttp")
public class OkHttpController {

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    private Lock lock = new ReentrantLock(true);

    public final String baseUrl = "http://www.klxiyou.com/api/scenic/";
    public final String supplierIdentity = "4569";
    public final String signkey = "3db0dc1a3dc6821bd0e7a5f772a73c0b";
    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new MyLoggingInterceptor())
            .build();

    /**
     * @author: 朱伟伟
     * @date: 2020-12-17 20:13
     * @description: 景区列表
     * 方案三：cors同源策略 解决跨域请求
     **/
    @GetMapping("/getScenicList")
    //默认全部
//    @CrossOrigin(origins = {"http://127.0.0.1:9090"})
//    public synchronized String getScenicList() throws Exception {
    public String getScenicList() throws Exception {
        try {
            readWriteLock.readLock().lock();
            lock.lock();
            FormBody formBody = new FormBody.Builder()
                    .add("supplierIdentity", supplierIdentity)
                    .add("signkey", signkey)
                    .build();
            MultipartBody multipartBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("supplierIdentity", supplierIdentity)
                    .addFormDataPart("signkey", signkey)
                    .build();
            Request request = new Request.Builder()
                    .url(baseUrl + "getScenicList")
                    .post(formBody)
//                .post(multipartBody)
                    .build();
            try (Response response = okHttpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                String result = response.body().string();
                System.out.println(response.body().contentType().toString());
                result = JSON.parseObject(result).toJSONString();
                response.body().close();
                response.close();
                return result;
            }
//            Thread.sleep(100);
//            return "";
        } finally {
            readWriteLock.readLock().unlock();
            lock.unlock();
        }
    }

}
