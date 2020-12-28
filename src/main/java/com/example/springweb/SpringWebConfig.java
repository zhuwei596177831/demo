package com.example.springweb;

import com.example.springweb.error.TestErrorAttributes;
import com.example.springweb.error.TestErrorController;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 朱伟伟
 * @date 2020-12-15 14:16:31
 * @description
 * @see org.springframework.web.multipart.support.StandardServletMultipartResolver
 * @see org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
 * <p>
 * handlerMappings:
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
 * @see org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping
 * @see org.springframework.web.servlet.function.support.RouterFunctionMapping
 * @see org.springframework.web.servlet.handler.SimpleUrlHandlerMapping
 * @see org.springframework.boot.autoconfigure.web.servlet.WelcomePageHandlerMapping
 * <p>
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
 * handlerFunctionAdapter -> {HandlerFunctionAdapter@6821}
 * httpRequestHandlerAdapter -> {HttpRequestHandlerAdapter@7060}
 * simpleControllerHandlerAdapter -> {SimpleControllerHandlerAdapter@7062}
 * <p>
 * errorAttributes -> {DefaultErrorAttributes@7117}
 * handlerExceptionResolver -> {HandlerExceptionResolverComposite@7119}
 * <p>
 * viewResolver -> {ContentNegotiatingViewResolver@6681}
 * beanNameViewResolver -> {BeanNameViewResolver@6679}
 * mvcViewResolver -> {ViewResolverComposite@7211}
 * defaultViewResolver -> {InternalResourceViewResolver@6799}
 */
@Configuration(proxyBeanMethods = false)
public class SpringWebConfig implements WebMvcConfigurer {
//    @Bean
//    public TestErrorAttributes testErrorAttributes() {
//        return new TestErrorAttributes();
//    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor1());
    }
}
