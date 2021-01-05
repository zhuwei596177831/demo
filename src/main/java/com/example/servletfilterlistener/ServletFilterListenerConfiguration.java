package com.example.servletfilterlistener;

import com.example.servletfilterlistener.filter.DirectBeanFilter;
import com.example.servletfilterlistener.filter.TestDelegatingFilter;
import com.example.servletfilterlistener.filter.TestDelegatingFilterFactoryBean;
import com.example.servletfilterlistener.filter.TestFilter;
import com.example.servletfilterlistener.listener.MyServletContextListener;
import com.example.servletfilterlistener.listener.MyServletRequestListener;
import com.example.servletfilterlistener.servlet.TestServlet;
import org.springframework.boot.web.servlet.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author 朱伟伟
 * @date 2020-09-21 15:11:28
 * @description
 */
@Configuration
//@ServletComponentScan(basePackageClasses = {ServletFilterListenerConfiguration.class})
public class ServletFilterListenerConfiguration {
    @Bean
    public TestServlet testServlet() {
        return new TestServlet();
    }

    @Bean
    public ServletRegistrationBean<TestServlet> testServletServletRegistrationBean(TestServlet testServlet) {
        return new ServletRegistrationBean<>(testServlet, "/testServlet");
    }

    @Bean
    public TestFilter testFilter() {
        return new TestFilter();
    }

    @Bean
    public FilterRegistrationBean<TestFilter> testFilterFilterRegistrationBean() {
        FilterRegistrationBean<TestFilter> testFilterFilterRegistrationBean = new FilterRegistrationBean<>(testFilter());
        testFilterFilterRegistrationBean.setEnabled(false);
        testFilterFilterRegistrationBean.setUrlPatterns(Collections.singletonList("/testFilter"));
        return testFilterFilterRegistrationBean;
    }

    @Bean
    public TestDelegatingFilterFactoryBean testDelegatingFilterFactoryBean() {
        return new TestDelegatingFilterFactoryBean();
    }

    @Bean
    public DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean() {
        return new DelegatingFilterProxyRegistrationBean("testDelegatingFilterFactoryBean");
    }


//    @Bean
//    public TestDelegatingFilter testDelegatingFilter() {
//        return new TestDelegatingFilter();
//    }


//    @Bean
//    public DirectBeanFilter directBeanFilter() {
//        return new DirectBeanFilter();
//    }

    @Bean
    ServletListenerRegistrationBean<MyServletRequestListener> servletRequestListenerServletListenerRegistrationBean() {
        return new ServletListenerRegistrationBean<>(new MyServletRequestListener());
    }

    /**
     * @param myHttpSessionListener:
     * @author: 朱伟伟
     * @date: 2020-12-11 16:40
     * @description: 失效
     **/
//    @Bean
//    ServletListenerRegistrationBean<MyHttpSessionListener> httpSessionListenerServletListenerRegistrationBean(MyHttpSessionListener myHttpSessionListener) {
//        return new ServletListenerRegistrationBean<>(new MyHttpSessionListener());
//    }
    @Bean
    ServletListenerRegistrationBean<MyServletContextListener> servletContextListenerServletListenerRegistrationBean() {
        return new ServletListenerRegistrationBean<>(new MyServletContextListener());
    }
}
