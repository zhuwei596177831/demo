package com.example.servletfilterlistener.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 朱伟伟
 * @date 2020-12-14 09:25:55
 * @description 将filter直接放在容器中 springboot会自动生成FilterRegistrationBean
 * @see org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext#selfInitialize
 * @see org.springframework.boot.web.servlet.ServletContextInitializerBeans
 */
public class DirectBeanFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("DirectBeanFilter doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("DirectBeanFilter init method");
    }
}
