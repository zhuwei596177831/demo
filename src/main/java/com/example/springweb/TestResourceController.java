package com.example.springweb;

import com.example.springweb.error.ErrorResult;
import com.example.springweb.error.TestErrorAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author 朱伟伟
 * @date 2020-12-22 17:33:22
 * @description
 */
@Controller
public class TestResourceController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/testResources")
    public String testResources(Model model) {
        httpServletRequest.setAttribute("timestamp", new Date());
        httpServletRequest.setAttribute("message", "message");
        return "testResources";
    }
}
