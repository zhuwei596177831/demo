package com.example.springweb.controller;

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
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 朱伟伟
 * @date 2020-12-22 17:33:22
 * @description
 */
@Controller
public class FreemarkerController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/freemarkerResources")
    public String testResources(Model model) {
        model.addAttribute("timestamp", LocalDateTime.now());
        return "freemarkerResources";
    }

    @RequestMapping(value = "/staticResources")
    public String staticResources(Model model) {
        return "staticResources";
    }
}
