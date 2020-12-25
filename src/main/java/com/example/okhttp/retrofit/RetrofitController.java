package com.example.okhttp.retrofit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import com.example.okhttp.retrofit.entity.BodyJson;
import com.example.okhttp.retrofit.entity.ScenicHttpResult;
import com.example.okhttp.retrofit.entity.ScenicList;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2020-12-17 21:10:06
 * @description
 */
@RestController
@RequestMapping("/retrofit")
public class RetrofitController implements InitializingBean {
    @Value("${klxiyou.baseUrl}")
    private String baseUrl;

    public final String supplierIdentity = "4569";
    public final String signkey = "3db0dc1a3dc6821bd0e7a5f772a73c0b";
    private Retrofit retrofit;

    private final OkHttpClient interceptorClient = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new MyLoggingInterceptor())
            .build();

    private final Retrofit localRetrofit = new Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8082/demo/retrofit/")
            .client(interceptorClient)
            .addConverterFactory(JacksonConverterFactory.create())
//            .addConverterFactory(new Retrofit2ConverterFactory())
//            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GetMapping("/getScenicList")
    public ScenicHttpResult<ScenicList> getScenicList(HttpServletRequest request, Model model, String name) throws IOException {
        System.out.println(Thread.currentThread().getName());
        GitHubService gitHubService = retrofit.create(GitHubService.class);
        Response<ScenicHttpResult<ScenicList>> response = gitHubService.getScenicList(supplierIdentity, signkey).execute();
        if (response.isSuccessful()) {
            return response.body();
        }
        return null;
    }

    @GetMapping("/getResponseBodyScenicList")
    public String getResponseBodyScenicList() throws IOException {
        GitHubService gitHubService = retrofit.create(GitHubService.class);
        Response<okhttp3.ResponseBody> response = gitHubService.getResponseBodyScenicList(supplierIdentity, signkey).execute();
        if (response.isSuccessful()) {
            MediaType mediaType = response.body().contentType();
            System.out.println(mediaType);
            String string = response.body().string();
            System.out.println(string);
            return string;
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(interceptorClient)
                .addConverterFactory(JacksonConverterFactory.create())
//                .addConverterFactory(new Retrofit2ConverterFactory())
//                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    /**
     * @author: 朱伟伟
     * @date: 2020-12-19 23:16
     * @description: retrofit 测试
     **/

    @GetMapping("/getBodyJson")
    public ScenicHttpResult<ScenicList> getBodyJson() throws IOException {
        GitHubService gitHubService = localRetrofit.create(GitHubService.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "朱伟伟");
        jsonObject.put("email", "596177831@qq.com");
        return gitHubService.retrofitBodyJson(
                "testPath",//path
//                jsonObject.toJSONString(),//body json
                jsonObject,//body json
//                new BodyJson("朱伟伟", "596177831@qq.com"),//body json
                "596177831",//query projectId
                Collections.singletonMap("projectName", "哈哈哈测试项目"),//query map
                "123456789ABCD",//header
                Collections.singletonMap("Authorization", "Authorization14511533"))//header map
                .execute().body();
    }

    /**
     * @param bodyJson:
     * @author: 朱伟伟
     * @date: 2020-12-18 22:47
     * @description: retrofit requestBody json接口
     **/
    @PostMapping("/retrofitBodyJson/{testPath}")
    public ScenicHttpResult<ScenicList> retrofitBodyJson(@PathVariable(name = "testPath") String testPath,
                                                         @RequestBody String bodyJson,
                                                         @RequestParam String projectId,
                                                         @RequestParam String projectName,
                                                         @RequestHeader(name = "accessKey") String accessKey,
                                                         @RequestHeader(name = "signKey") String signKey,
                                                         @RequestHeader(name = "Authorization") String Authorization) throws IOException {
        System.out.println("PathVariable testPath：" + testPath);
        System.out.println("RequestBody bodyJson：" + bodyJson);
        BodyJson bodyJson1 = JSON.parseObject(bodyJson, BodyJson.class);
        System.out.println("RequestParam projectId：" + projectId);
        System.out.println("RequestParam projectName：" + projectName);
        System.out.println("RequestHeader accessKey：" + accessKey);
        System.out.println("RequestHeader signKey：" + signKey);
        System.out.println("RequestHeader Authorization：" + Authorization);
        return getScenicList(null, null, null);
    }

    /**
     * @author: 朱伟伟
     * @date: 2020-12-19 23:22
     * @description: retrofit文件测试接口
     **/

    @GetMapping("/getRetrofitFile")
    public ScenicHttpResult<ScenicList> getRetrofitFile() throws IOException {
        GitHubService gitHubService = localRetrofit.create(GitHubService.class);
        File file = new File("D:\\测试retrofit.jpg");
        okhttp3.RequestBody fileRequestBody = okhttp3.RequestBody.create(null, file);
        List<MultipartBody.Part> parts = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("studentName", "朱伟伟")
                .addFormDataPart("imageFile", file.getPath(), fileRequestBody)
                .build().parts();
        return gitHubService.retrofitMultipartBodyFile(parts).execute().body();
    }

    @PutMapping("/retrofitMultipartBodyFile")
    public ScenicHttpResult<ScenicList> retrofitMultipartBodyFile(String studentName, MultipartFile imageFile) throws IOException {
        System.out.println("studentName：" + studentName);
        System.out.println("图片文件名称：" + imageFile.getOriginalFilename());
        System.out.println("图片文件大小：" + imageFile.getSize());
        return getScenicList(null, null, null);
    }


}
