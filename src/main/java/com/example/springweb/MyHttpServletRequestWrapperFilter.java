package com.example.springweb;

import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 朱伟伟
 * @date 2021-01-04 17:25:30
 * @description
 */
@Component
public class MyHttpServletRequestWrapperFilter extends OncePerRequestFilter implements OrderedFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new MyHttpServletRequestWrapper(request), response);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
