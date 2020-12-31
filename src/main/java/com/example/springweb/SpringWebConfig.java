package com.example.springweb;

import com.example.springweb.HandlerInterceptor.FileHandlerInterceptor;
import com.example.springweb.HandlerInterceptor.MappedHandlerInterceptor;
import com.example.springweb.customPathPrefix.ApiScenicPrefix;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

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
 * {@link org.springframework.boot.web.servlet.error.DefaultErrorAttributes}
 * {@link org.springframework.web.servlet.handler.HandlerExceptionResolverComposite}
 * <p>
 * viewResolver -> {ContentNegotiatingViewResolver@6681}
 * beanNameViewResolver -> {BeanNameViewResolver@6679}
 * mvcViewResolver -> {ViewResolverComposite@7211}
 * defaultViewResolver -> {InternalResourceViewResolver@6799}
 */
@Configuration(proxyBeanMethods = false)
@PropertySource(value = {"classpath:patchPrefix.properties"})
public class SpringWebConfig implements WebMvcConfigurer {
    @Value("${scenic.prefix}")
    private String scenicPrefix;
//    @Bean
//    public TestErrorAttributes testErrorAttributes() {
//        return new TestErrorAttributes();
//    }

    @Bean
    LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    @Bean
    MappedInterceptor mappedInterceptor() {
        return new MappedInterceptor(new String[]{"/testMappedInterceptor"}, new String[]{}, new MappedHandlerInterceptor());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FileHandlerInterceptor()).addPathPatterns("/testFile");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //"close /users" also matches to "/users.*". 默认已关闭
        configurer.setUseSuffixPatternMatch(false);
        configurer.setUseRegisteredSuffixPatternMatch(false);
        //"close /users" also matches to "/users/". 默认开启
        configurer.setUseTrailingSlashMatch(false);
        //添加自定义RequestMapping prefix 支持占位符 http://127.0.0.1:8082/demo/api/scenic/v1/retrofit/getScenicList
//        configurer.addPathPrefix("${scenic.prefix}", ApiScenicPrefix.class::isAssignableFrom);
//        configurer.addPathPrefix(scenicPrefix, ApiScenicPrefix.class::isAssignableFrom);
        HandlerTypePredicate predicate = HandlerTypePredicate.forBasePackage("com.example.okhttp");
        configurer.addPathPrefix(scenicPrefix, predicate);
    }
}
