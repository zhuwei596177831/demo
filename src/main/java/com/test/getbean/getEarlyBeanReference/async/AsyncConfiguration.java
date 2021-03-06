package com.test.getbean.getEarlyBeanReference.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 朱伟伟
 * @date 2020-12-04 10:01:32
 * @description
 * @see org.springframework.scheduling.annotation.AsyncConfigurationSelector
 * @see org.springframework.scheduling.annotation.ProxyAsyncConfiguration
 * @see AsyncConfigurer
 * @see org.springframework.scheduling.annotation.AsyncAnnotationBeanPostProcessor
 * @see org.springframework.scheduling.annotation.AsyncAnnotationAdvisor
 * @see org.springframework.scheduling.annotation.AnnotationAsyncExecutionInterceptor
 * @see org.springframework.scheduling.annotation.Async
 * 1、注解一般用在方法上，如果用在类上，那么这个类所有的方法都是异步执行的；
 * 2、可以放在任何方法上，哪怕你是private的（若是同类调用，请务必注意注解失效的情况~~~）
 * 3、所使用的@Async注解方法的类对象应该是Spring容器管理的bean对象
 * 4、可以放在接口处（或者接口方法上）。但是只有使用的是JDK的动态代理时才有效，CGLIB会失效。因此建议：统一写在实现类的方法上
 * 5、需要注解@EnableAsync来开启异步注解的支持
 * 6、若你希望得到异步调用的返回值，请你的返回值用Futrue变量包装起来
 */
@Configuration(proxyBeanMethods = false)
//@Async注解所在的类 强制使用cglib代理 注意区分：
// @EnableAspectJAutoProxy(proxyTargetClass = true)、@EnableTransactionManagement(proxyTargetClass = true)
// AbstractAutoProxyCreator(处理aop、transactional)
@EnableAsync(proxyTargetClass = true)
public class AsyncConfiguration {

    @Bean
    AsyncConfigurer asyncConfigurer() {
        return new MyAsyncConfigurer();
    }

}
