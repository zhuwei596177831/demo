package com.example.springweb;

import com.example.springweb.HandlerInterceptor.FileHandlerInterceptor;
import com.example.springweb.HandlerInterceptor.MappedHandlerInterceptor;
import com.example.springweb.support.MyLocaleResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.MappedInterceptor;

import java.util.List;

/**
 * @author 朱伟伟
 * @date 2020-12-15 14:16:31
 * @description multipartResolver:
 * @see org.springframework.web.multipart.support.StandardServletMultipartResolver
 * @see org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
 * <p>
 * handlerMappings:
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
 * @see org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping
 * @see org.springframework.web.servlet.function.support.RouterFunctionMapping
 * @see org.springframework.web.servlet.handler.SimpleUrlHandlerMapping
// * @see org.springframework.boot.autoconfigure.web.servlet.WelcomePageHandlerMapping
 * <p>
 * handlerAdapters:
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
 * @see org.springframework.web.servlet.function.support.HandlerFunctionAdapter
 * @see org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter {@link org.springframework.web.HttpRequestHandler}
 * @see org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter
 * <p>
 * handlerExceptionResolvers:
 * {@link org.springframework.boot.web.servlet.error.DefaultErrorAttributes}
 * {@link org.springframework.web.servlet.handler.HandlerExceptionResolverComposite}
 * <p>
 * viewResolvers:
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

    /**
     * @param configurer:
     * @author: 朱伟伟
     * @date: 2021-01-05 11:28
     * @description: 配置controller下{{@link org.springframework.web.bind.annotation.RequestMapping}}http统一请求前缀
     **/
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

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/",
            "classpath:/resources/", "classpath:/static/", "classpath:/public/"};

    /**
     * @param registry:
     * @author: 朱伟伟
     * @date: 2021-01-05 11:24
     * @description: 配置静态资源处理方式一：
     * {@link org.springframework.web.servlet.resource.ResourceHttpRequestHandler}
     * {@link org.springframework.web.servlet.handler.SimpleUrlHandlerMapping}
     * {@link ResourceHandlerRegistration}
     * @see org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#addResourceHandlers
     **/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
        registry.addResourceHandler("/public/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    /**
     * @param configurer:
     * @author: 朱伟伟
     * @date: 2021-01-05 11:23
     * @description: 配置静态资源处理方式二：
     * {@link org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler}
     * {@link org.springframework.web.servlet.handler.SimpleUrlHandlerMapping}
     **/
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable("defaultServletHandling");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        HandlerMethodArgumentResolver argumentResolver = new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return false;
            }

            @Override
            public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
                return null;
            }
        };
        resolvers.add(argumentResolver);
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    }
}
