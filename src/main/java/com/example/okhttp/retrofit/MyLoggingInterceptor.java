package com.example.okhttp.retrofit;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author 朱伟伟
 * @date 2020-12-21 14:54:56
 * @description
 */
public class MyLoggingInterceptor implements Interceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logger.info("sending request url:\n{}", request.url());
        logger.info("sending request headers:\n{}", request.headers());
        RequestBody requestBody = request.body();
        logger.info("contentType:\n{}", requestBody.contentType());
        if (requestBody instanceof MultipartBody) {
            MultipartBody multipartBody = (MultipartBody) requestBody;
            System.out.println(multipartBody.boundary());
            for (MultipartBody.Part part : multipartBody.parts()) {
                System.out.println(part);
            }
        } else {
            Buffer buffer = new Buffer();
            Objects.requireNonNull(requestBody).writeTo(buffer);
            InputStream inputStream = buffer.inputStream();
            int available = inputStream.available();
//        byte[] bytes = new byte[1024];
            byte[] bytes = new byte[available];
//        int len;
            int read = inputStream.read(bytes);
            //        while ((len = inputStream.read(bytes)) != -1) {
//            stringBuilder.append(new String(bytes, 0, len));
//        }
            logger.info("sending body data:\n{}", new String(bytes, 0, read));
//        while ((len = inputStream.read(bytes)) != -1) {
//            stringBuilder.append(new String(bytes, 0, len));
//        }
        }
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.peekBody(Integer.MAX_VALUE);
        logger.info("receive data:\n{}", JSON.parseObject(new String(responseBody.bytes())).toJSONString());
        return response;
    }
}
