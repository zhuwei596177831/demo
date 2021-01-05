package com.example.springweb.customHandlerMapping;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 朱伟伟
 * @date 2020-12-31 10:55:47
 * @description 自定义一个handler beanName必须以/开头
 * @see org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping
 * @see org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter
 */
@Component("/customHttpRequestHandler")
public class CustomHttpRequestHandler implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
