package com.example.springweb;

import com.example.springweb.HandlerInterceptor.FileHandlerInterceptor;
import com.example.springweb.HandlerInterceptor.MappedHandlerInterceptor;
import com.example.springweb.converter.StringToMapConverter;
import com.example.springweb.support.MyLocaleResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.MethodParameter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
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
 * // * @see org.springframework.boot.autoconfigure.web.servlet.WelcomePageHandlerMapping
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
@PropertySource(value = {"classpath:patchPrefix.properties", "classpath:resources.properties"})
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

        /**
         * @param configurer:
         * @author: 朱伟伟
         * @date: 2021-01-13 10:03
         * @description: 开启矩阵变量
         * @see org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping#handleMatch
         * @see WebMvcConfigurationSupport#requestMappingHandlerMapping
         * @see WebMvcConfigurationSupport#getPathMatchConfigurer
         **/
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
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

    /**
     * @param converters:
     * @author: 朱伟伟
     * @date: 2021-01-14 17:49
     * @description: 自定义添加 {@link HttpMessageConverter}
     **/
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    /**
     * @param converters:
     * @author: 朱伟伟
     * @date: 2021-01-14 17:49
     * @description: This may be useful for example to allow default converters to be registered
     * and then insert a custom converter through this method.
     **/
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    /**
     * @param resolvers:
     * @author: 朱伟伟
     * @date: 2021-01-14 17:46
     * @description: 自定义 {@link HandlerMethodArgumentResolver}
     * 排在spring默认添加之后
     * @see RequestMappingHandlerAdapter#afterPropertiesSet()
     **/
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

    /**
     * @param handlers:
     * @author: 朱伟伟
     * @date: 2021-01-14 17:46
     * @description: 自定义 {@link HandlerMethodReturnValueHandler}
     * 排在spring默认添加之后
     * @see RequestMappingHandlerAdapter#afterPropertiesSet()
     **/
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    }

    /**
     * @param formatterRegistry:
     * @author: 朱伟伟
     * @date: 2021-01-11 15:27
     * @description:
     **/
    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
//        formatterRegistry.addConverter(new StringToMapConverter());
    }
}
