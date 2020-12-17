package com.example.okhttp.retrofit;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

/**
 * @author 朱伟伟
 * @date 2020-12-17 21:10:06
 * @description
 */
@RestController
@RequestMapping("/retrofit")
public class RetrofitController {

    public final String supplierIdentity = "4569";
    public final String signkey = "3db0dc1a3dc6821bd0e7a5f772a73c0b";

    @Autowired
    ScenicHttpService scenicHttpService;

    @GetMapping("/getScenicList")
    public String getScenicList() throws IOException {
//        Response<String> response = scenicHttpService.getScenicList(supplierIdentity, signkey);
        Call<String> call = scenicHttpService.getScenicList(supplierIdentity, signkey);
        Response<String> response = call.execute();
        if (response.isSuccessful()) {
//        result = JSON.parseObject(result).toJSONString();
            System.out.println(response.body());
            return response.body();
        }
        return null;
    }

}
