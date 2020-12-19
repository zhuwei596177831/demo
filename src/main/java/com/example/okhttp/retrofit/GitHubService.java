package com.example.okhttp.retrofit;

import com.example.okhttp.retrofit.entity.ScenicHttpResult;
import com.example.okhttp.retrofit.entity.ScenicList;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2020-12-18 00:08:30
 * @description
 */
public interface GitHubService {
    /**
     * @param supplierIdentity:
     * @param signkey:
     * @author: 朱伟伟
     * @date: 2020-12-20 00:34
     * @description: application/x-www-form-urlencoded form-data 格式
     **/
    @FormUrlEncoded
    @POST("getScenicList")
    Call<ScenicHttpResult<ScenicList>> getScenicList(@Field("supplierIdentity") String supplierIdentity, @Field("signkey") String signkey);

    @FormUrlEncoded
    @POST("getScenicList")
    Call<ResponseBody> getResponseBodyScenicList(@Field("supplierIdentity") String supplierIdentity, @Field("signkey") String signkey);

    /**
     * @param testPath:
     * @param bodyJson:
     * @param projectId:
     * @param queryMap:
     * @param accessKey:
     * @param headerMap:
     * @author: 朱伟伟
     * @date: 2020-12-20 00:34
     * @description: body json 格式
     **/
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

    /**
     * @param parts:
     * @author: 朱伟伟
     * @date: 2020-12-20 00:35
     * @description: multipart/form-data格式
     **/
    @Multipart
    @PUT("retrofitMultipartBodyFile")
    Call<ScenicHttpResult<ScenicList>> retrofitMultipartBodyFile(@Part List<MultipartBody.Part> parts);

}
