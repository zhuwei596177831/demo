package com.example.okhttp.retrofit;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * @author 朱伟伟
 * @date 2020-12-17 21:14:27
 * @description
 */
@RetrofitClient(baseUrl = "${klxiyou.baseUrl}")
public interface ScenicHttpService {
    @FormUrlEncoded
    @POST("getScenicList")
    Call<String> getScenicList(@Field("supplierIdentity") String supplierIdentity, @Field("signkey") String signkey);
}
