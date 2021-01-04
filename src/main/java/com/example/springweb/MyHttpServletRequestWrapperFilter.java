package com.example.springweb;

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
public class MyHttpServletRequestWrapperFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new MyHttpServletRequestWrapper(request), response);
//        filterChain.doFilter(new MyRequestWrapper(request), response);
    }
}
