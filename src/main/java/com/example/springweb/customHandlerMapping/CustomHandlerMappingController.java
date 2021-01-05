package com.example.springweb.customHandlerMapping;

import com.example.generic.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 朱伟伟
 * @date 2020-12-31 10:55:47
 * @description 自定义一个handler beanName必须以/开头
 * @see org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping
 * @see org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter
 */
@Controller("/customHandlerMappingController")
public class CustomHandlerMappingController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("sss");
    }
}
