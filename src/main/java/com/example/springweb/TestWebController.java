package com.example.springweb;

import com.example.generic.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱伟伟
 * @date 2020-12-30 17:20:28
 * @description
 */
@RestController
public class TestWebController {

    @PostMapping("/testHttpServletRequestWrapper")
    public Result testHttpServletRequestWrapper(@RequestBody String body) {
        return Result.ok();
    }

}
