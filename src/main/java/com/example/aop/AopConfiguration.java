package com.example.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 朱伟伟
 * @date 2020-09-14 10:08:42
 * @description
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
public class AopConfiguration {

    @Bean
    public UserComponent userComponent() {
        return new UserComponent();
    }

    @Bean
    public PersonComponent personComponent() {
        return new PersonComponent();
    }

    @Bean
    public MyAspect myAspect() {
        return new MyAspect();
    }

}
