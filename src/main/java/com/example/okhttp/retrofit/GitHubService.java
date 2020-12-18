package com.example.okhttp.retrofit;

import com.example.okhttp.retrofit.entity.ScenicHttpResult;
import com.example.okhttp.retrofit.entity.ScenicList;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2020-12-18 00:08:30
 * @description
 */
public interface GitHubService {
    @FormUrlEncoded
    @POST("getScenicList")
    Call<ScenicHttpResult<ScenicList>> getScenicList(@Field("supplierIdentity") String supplierIdentity, @Field("signkey") String signkey);

    @FormUrlEncoded
    @POST("getScenicList")
    Call<ResponseBody> getStringScenicList(@Field("supplierIdentity") String supplierIdentity, @Field("signkey") String signkey);

    @Headers({
            "signKey:147895336",
    })
    @POST("retrofitBodyJson/{testPath}")
    Call<ScenicHttpResult<ScenicList>> retrofitBodyJson(@Path(value = "testPath") String testPath,
                                                        @Body String bodyJson,
                                                        @Query(value = "projectId") String projectId,
                                                        @QueryMap Map<String, String> queryMap,
                                                        @Header("accessKey") String accessKey,
                                                        @HeaderMap Map<String, String> headerMap);

    @Multipart
    @PUT("retrofitMultipart")
    Call<ScenicHttpResult<ScenicList>> retrofitMultipart(@Part(value = "idPic") RequestBody idPic);
}
