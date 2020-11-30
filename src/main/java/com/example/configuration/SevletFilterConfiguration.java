package com.example.configuration;

import com.example.configuration.servlet.TestFilter;
import com.example.configuration.servlet.TestServlet;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author 朱伟伟
 * @date 2020-09-21 15:11:28
 * @description
 */
@Configuration(proxyBeanMethods = false)
public class SevletFilterConfiguration {

    @Bean
    public TestServlet testServlet() {
        return new TestServlet();
    }

    @Bean
    public ServletRegistrationBean<TestServlet> testServletServletRegistrationBean(TestServlet testServlet) {
        return new ServletRegistrationBean<>(testServlet, "/testServlet");
//        return new ServletRegistrationBean<>(testServlet, "/getTest");
    }

    @Bean
    public TestFilter testFilter() {
        return new TestFilter();
    }

    @Bean
    public DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean() {
        DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean = new DelegatingFilterProxyRegistrationBean("testFilter");
        return delegatingFilterProxyRegistrationBean;
    }

}
