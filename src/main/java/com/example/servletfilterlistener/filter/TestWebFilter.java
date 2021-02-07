package com.example.servletfilterlistener.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author 朱伟伟
 * @date 2020-12-11 18:00:43
 * @description
 */
@WebFilter(filterName = "testWebFilter", urlPatterns = {"/testWebFilter"})//注册为FilterRegistrationBean类型
//@Component
public class TestWebFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(TestWebFilter.class);


    public TestWebFilter() {
        System.out.println("");
    }

    @Autowired
    private ApplicationContext applicationContext;



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("######TestWebFilter doFilter");
        chain.doFilter(request, response);
    }
}
