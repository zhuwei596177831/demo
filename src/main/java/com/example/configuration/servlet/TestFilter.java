package com.example.configuration.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 朱伟伟
 * @date 2020-09-21 17:42:09
 * @description
 */
public class TestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }
}
