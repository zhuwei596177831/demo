package com.example.okhttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import okhttp3.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2020-12-17 20:11:50
 * @description
 */
@RestController
@RequestMapping("/okHttp")
public class OkHttpController {

    public final String baseUrl = "http://www.klxiyou.com/api/scenic/";
    public final String supplierIdentity = "4569";
    public final String signkey = "3db0dc1a3dc6821bd0e7a5f772a73c0b";
    private final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    /**
     * @author: 朱伟伟
     * @date: 2020-12-17 20:13
     * @description: 景区列表
     **/
    @GetMapping("/getScenicList")
    public String getScenicList() throws Exception {
        RequestBody requestBody = new FormBody.Builder()
                .add("supplierIdentity", supplierIdentity)
                .add("signkey", signkey)
                .build();
        Request request = new Request.Builder()
                .url(baseUrl + "getScenicList")
                .post(requestBody)
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String result = response.body().string();
            System.out.println(response.body().contentType().toString());
            result = JSON.parseObject(result).toJSONString();
            System.out.println(result);
            return result;
        }
    }

}