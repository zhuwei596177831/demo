package com.example.springweb.controller;

import com.example.generic.Result;
import com.example.springweb.error.ErrorResult;
import com.example.springweb.error.TestErrorAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

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

    /**
     * @param redirectAttributes: 重定向页面可携带参数
     * @author: 朱伟伟
     * @date: 2021-01-20 13:42
     * @description: {@link org.springframework.web.servlet.mvc.method.annotation.RedirectAttributesMethodArgumentResolver}
     **/
    @GetMapping("/redirectAttributesMethodArgumentResolver")
    public String redirectAttributesMethodArgumentResolver(RedirectAttributes redirectAttributes) {
        return "redirect:redirectTest";
    }

    /**
     * @param model:
     * @param map:
     * @author: 朱伟伟
     * @date: 2021-01-20 13:54
     * @description: {@link org.springframework.web.method.annotation.ModelMethodProcessor}
     * {@link org.springframework.web.method.annotation.MapMethodProcessor}
     **/
    @GetMapping("/modelOrMapMethod")
    public String modelOrMapMethod(Model model, Map map) {
        return "freemarkerResources";
    }


}
