package com.example.springweb;

import com.example.springweb.error.TestErrorAttributes;
import com.example.springweb.error.TestErrorController;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 朱伟伟
 * @date 2020-12-15 14:16:31
 * @description
 * @see org.springframework.web.multipart.support.StandardServletMultipartResolver
 * @see org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
 * <p>
 * handlerMappings:
 * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
 * beanNameHandlerMapping -> {BeanNameUrlHandlerMapping@6975}
 * routerFunctionMapping -> {RouterFunctionMapping@6807}
 * resourceHandlerMapping -> {SimpleUrlHandlerMapping@6977}
 * welcomePageHandlerMapping -> {WelcomePageHandlerMapping@6973}
 * <p>
 * requestMappingHandlerAdapter -> {RequestMappingHandlerAdapter@6830}
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
public class SpringWebConfig {
//    @Bean
//    public TestErrorAttributes testErrorAttributes() {
//        return new TestErrorAttributes();
//    }

}
