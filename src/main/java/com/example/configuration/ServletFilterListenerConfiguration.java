package com.example.configuration;

import com.example.configuration.filter.TestFilter;
import com.example.configuration.listener.MyHttpSessionListener;
import com.example.configuration.listener.MyServletContextListener;
import com.example.configuration.listener.MyServletRequestListener;
import com.example.configuration.servlet.TestServlet;
import org.springframework.boot.web.servlet.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 朱伟伟
 * @date 2020-09-21 15:11:28
 * @description
 */
@Configuration
@ServletComponentScan(basePackageClasses = {ServletFilterListenerConfiguration.class})
public class ServletFilterListenerConfiguration {

//    @Bean
//    public TestServlet testServlet() {
//        return new TestServlet();
//    }

//    @Bean
//    public ServletRegistrationBean<TestServlet> testServletServletRegistrationBean(TestServlet testServlet) {
//        ServletRegistrationBean<TestServlet> testServletServletRegistrationBean = new ServletRegistrationBean<>(testServlet, "/testServlet");
//        testServletServletRegistrationBean.setEnabled(true);
//        return testServletServletRegistrationBean;
//    }

    @Bean
    public TestFilter testFilter() {
        return new TestFilter();
    }

    @Bean
    public FilterRegistrationBean<TestFilter> testFilterFilterRegistrationBean() {
//        FilterRegistrationBean<TestFilter> testFilterFilterRegistrationBean = new FilterRegistrationBean<>(new TestFilter());
        FilterRegistrationBean<TestFilter> testFilterFilterRegistrationBean = new FilterRegistrationBean<>(testFilter());
//        testFilterFilterRegistrationBean.setEnabled(false);
        return testFilterFilterRegistrationBean;
    }

//    @Bean
//    public DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean() {
//        DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean = new DelegatingFilterProxyRegistrationBean("testFilter");
//        return delegatingFilterProxyRegistrationBean;
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
