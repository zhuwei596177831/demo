package com.example.servletfilterlistener.filter;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2020-12-14 18:18:10
 * @description delegating filter
 */
public class TestDelegatingFilter implements Filter {
    /**
     * ServletComponentScan扫描的filter无法注入进来
     */
    @Autowired
    private Map<String, Filter> filterMap;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("TestDelegatingFilter doFilter");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String servletPath = httpServletRequest.getServletPath();
        Filter filter = filterMap.get(servletPath.replace("/", ""));
        if (filter == null) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendRedirect("http://www.baidu.com");
            return;
        }
        filter.doFilter(request, response, chain);
    }

    @Override
    public void destroy() {

    }
}
