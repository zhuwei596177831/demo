package com.example.springweb;

import com.example.generic.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 朱伟伟
 * @date 2020-12-30 17:20:28
 * @description
 */
@RestController
public class TestWebController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @PostMapping("/testHttpServletRequestWrapper")
    public Result testHttpServletRequestWrapper(@RequestBody String body) {
        return Result.ok();
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-01-04 14:47
     * @description: getParameter获取Body体参数（Content-Type：application/x-www-form-urlencoded）
     * POST(servlet原生支持)
     * PUT PATCH DELETE {@link org.springframework.boot.web.servlet.filter.OrderedFormContentFilter}
     * <p>
     * other: {@link OrderedHiddenHttpMethodFilter}
     * {@link org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter}
     * {@link org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter}
     **/
    @PostMapping("/getParameter")
//    @PutMapping("/getParameter")
//    @DeleteMapping("/getParameter")
//    public Result getParameter(@RequestBody String data) {
    public Result getParameter(String helloName) {
        String name = httpServletRequest.getParameter("helloName");
        return Result.ok();
    }


}
