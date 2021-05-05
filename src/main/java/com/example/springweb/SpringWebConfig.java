package com.example.springweb;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.springweb.HandlerInterceptor.FileHandlerInterceptor;
import com.example.springweb.HandlerInterceptor.MappedHandlerInterceptor;
import com.example.springweb.converter.StringToMapConverter;
import com.example.springweb.support.MyHandlerMethodArgumentResolver;
import com.example.springweb.support.MyLocaleResolver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.MethodParameter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

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
public class SpringWebConfig implements WebMvcConfigurer, BeanPostProcessor {

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
     * 默认提供的converter:
     * {@link org.springframework.http.converter.ByteArrayHttpMessageConverter} application/octet-stream、/* defaultCharset:null
     * {@link org.springframework.http.converter.StringHttpMessageConverter} text/plain、/*  defaultCharset:UTF-8
     * {@link org.springframework.http.converter.StringHttpMessageConverter} text/plain、/*  defaultCharset:ISO-8859-1
     * {@link org.springframework.http.converter.ResourceHttpMessageConverter}   defaultCharset:null
     * {@link org.springframework.http.converter.ResourceRegionHttpMessageConverter}   defaultCharset:null
     * <p>
     * application/xml、text/xml、application/*+xml defaultCharset:null
     * {@link org.springframework.http.converter.xml.SourceHttpMessageConverter}
     * <p>
     * application/x-www-form-urlencoded、multipart/form-data、multipart/mixed defaultCharset:UTF-8
     * {@link org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter}
     * <p>
     * application/json、application/*+json defaultCharset:null
     * {@link org.springframework.http.converter.json.MappingJackson2HttpMessageConverter} 两个 暂未明白为啥????
     * <p>
     * application/xml、text/xml、application/*+xml defaultCharset:null
     * {@link org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter}
     **/
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //[application/octet-stream, text/plain, application/xml, text/xml, application/x-www-form-urlencoded,
        // application/*+xml, multipart/form-data, multipart/mixed, application/json, application/*+json, */*]
//        converters.add(new FastJsonHttpMessageConverter());
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
     * @author: 朱伟伟
     * @date: 2021-01-21 10:28
     * @description: springboot方式配置自定义的 {@link HttpMessageConverter}使其放在最前面
     * @see org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration#messageConverters
     * @see org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#configureMessageConverters
     **/
    @Bean
    FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //限制只处理 application/json Content-Type类型的数据转换  默认*/* !!!!!
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat
        );
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return fastJsonHttpMessageConverter;
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
//        resolvers.add(new MyHandlerMethodArgumentResolver());
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
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); //核心线程数
        executor.setMaxPoolSize(20);  //最大线程数
        executor.setQueueCapacity(1000); //队列大小
        executor.setKeepAliveSeconds(300); //线程最大空闲时间
        executor.setThreadNamePrefix("mvc-async-Executor-"); //指定用于新创建的线程名称的前缀。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略（一共四种，此处省略）
        // 这一步千万不能忘了，否则报错： java.lang.IllegalStateException: ThreadPoolTaskExecutor not initialized
        executor.initialize();
        configurer.setTaskExecutor(executor);
//        return new SimpleAsyncTaskExecutor();
    }

    /**
     * @param formatterRegistry:
     * @author: 朱伟伟
     * @date: 2021-01-11 15:27
     * @description: 全局添加自定义的converter
     **/
    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
//        formatterRegistry.addConverter(new StringToMapConverter());
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-01-20 17:45
     * @description: 添加自定义HandlerMethodArgumentResolver
     **/
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RequestMappingHandlerAdapter) {
            RequestMappingHandlerAdapter handlerAdapter = (RequestMappingHandlerAdapter) bean;
            List<HandlerMethodArgumentResolver> argumentResolvers = handlerAdapter.getArgumentResolvers();
            if (argumentResolvers != null) {
                argumentResolvers = new ArrayList<>(argumentResolvers);
                argumentResolvers.add(0, new MyHandlerMethodArgumentResolver());
                handlerAdapter.setArgumentResolvers(argumentResolvers);
            }
        }
        return bean;
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-01-21 14:29
     * @description: 自定义添加HandlerExceptionResolver(会覆盖spring提供的默认的)
     * @see WebMvcConfigurationSupport#addDefaultHandlerExceptionResolvers
     * {@link org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver}
     * {@link org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver}
     * {@link org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver}
     *
     * <li>{@link org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver} for handling exceptions through
     * {@link org.springframework.web.bind.annotation.ExceptionHandler} methods.
     * <li>{@link org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver} for exceptions annotated with
     * {@link org.springframework.web.bind.annotation.ResponseStatus}.
     * <li>{@link org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver} for resolving known Spring exception types
     **/
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    /**
     * @param corsRegistry:
     * @author: 朱伟伟
     * @date: 2021-05-05 17:53
     * @description: 其实实现CORS很简单，就是在服务端加一些响应头，并且这样做对前端来说是无感知的，很方便。
     * 详解响应头：
     * <p>
     * Access-Control-Allow-Origin 该字段必填。它的值要么是请求时Origin字段的具体值，要么是一个*，表示接受任意域名的请求。
     * <p>
     * Access-Control-Allow-Methods 该字段必填。它的值是逗号分隔的一个具体的字符串或者*，表明服务器支持的所有跨域请求的方法。
     * 注意，返回的是所有支持的方法，而不单是浏览器请求的那个方法。这是为了避免多次"预检"请求。
     * <p>
     * Access-Control-Expose-Headers 该字段可选。CORS请求时，XMLHttpRequest对象的getResponseHeader()方法只能拿到6个基本字段：
     * Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma。
     * 如果想拿到其他字段，就必须在Access-Control-Expose-Headers里面指定。
     * <p
     * Access-Control-Allow-Credentials 该字段可选。它的值是一个布尔值，表示是否允许发送Cookie.默认情况下，不发生Cookie，
     * 即：false。对服务器有特殊要求的请求，比如请求方法是PUT或DELETE，或者Content-Type字段的类型是application/json，
     * 这个值只能设为true。如果服务器不要浏览器发送Cookie，删除该字段即可。
     * <p>
     * Access-Control-Max-Age 该字段可选，用来指定本次预检请求的有效期，单位为秒。在有效期间，不用发出另一条预检请求。
     * <p>
     * 顺便提一下，如果在开发中，发现每次发起请求都是两条，一次OPTIONS，一次正常请求，注意是每次，
     * 那么就需要配置Access-Control-Max-Age，避免每次都发出预检请求
     * <p>
     * 方案一：cors同源策略 解决跨域请求
     **/
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry
                .addMapping("/**")
//                .allowedOrigins("*")
//                .allowedOrigins("http://127.0.0.1:9090")
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name())
                .allowCredentials(true)
                //By default this is set to 1800 seconds (30 minutes).
                .maxAge(3600)
//                .allowedHeaders("*")
        ;
    }
}
