package com.example.configuration;

import com.example.interceptor.Interceptor1;
import com.example.interceptor.Interceptor2;
import com.example.interceptor.Interceptor3;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 朱伟伟
 * @date 2020-09-22 16:54:23
 * @description
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor1()).addPathPatterns("/**");
        registry.addInterceptor(new Interceptor2()).addPathPatterns("/**");
        registry.addInterceptor(new Interceptor3()).addPathPatterns("/**");
    }
}
