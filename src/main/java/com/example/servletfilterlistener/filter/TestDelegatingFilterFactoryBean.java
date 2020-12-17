package com.example.servletfilterlistener.filter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2020-12-14 18:46:44
 * @description
 */
public class TestDelegatingFilterFactoryBean implements FactoryBean<Filter>, BeanPostProcessor {
    private final Map<String, Filter> filterMap = new LinkedHashMap<>(4);

    @Override
    public Filter getObject() throws Exception {
        return new TestDelegatingFilterFactoryBeanFilter();
    }

    @Override
    public Class<?> getObjectType() {
        return TestDelegatingFilterFactoryBeanFilter.class;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Filter) {
            filterMap.put(beanName, (Filter) bean);
        }
        return bean;
    }

    class TestDelegatingFilterFactoryBeanFilter implements Filter {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            System.out.println("TestDelegatingFilterFactoryBeanFilter doFilter");
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String servletPath = httpServletRequest.getServletPath();
            Filter filter = filterMap.get(servletPath.replace("/", ""));
            if (filter == null) {
//                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//                httpServletResponse.sendRedirect("http://www.baidu.com");
                chain.doFilter(request, response);
                return;
            }
            filter.doFilter(request, response, chain);
        }
    }


}
