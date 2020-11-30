package com.example.getbean;

import org.springframework.context.annotation.*;

/**
 * @author 朱伟伟
 * @date 2020-11-25 10:04:42
 * @description
 */
@Configuration(proxyBeanMethods = false)
//@Configuration
@ComponentScan(basePackageClasses = {GetBeanConfiguration.class})
@EnableAspectJAutoProxy
public class GetBeanConfiguration {


    /**
     * @author: 朱伟伟
     * @date: 2020-11-26 16:01
     * @description:
     * 单例bean循环依赖失效情况：
     * 1、@Component constructor方式互相注入
     * 2、@Bean互相引用
     * 3、@Component(constructor方式注入) @Bean互相引用
     **/

    @Bean
    public SingletonBeanA singletonBeanA(CommonBean commonBean) {
        return new SingletonBeanA();
    }

    @Bean
    public SingletonBeanB singletonBeanB(SingletonBeanA singletonBeanA) {
        return new SingletonBeanB();
    }

//    @Bean
//    public SingletonBeanC singletonBeanC() {
//        return new SingletonBeanC(singletonBeanD());
//    }
//
//    @Bean
//    public SingletonBeanD singletonBeanD() {
//        return new SingletonBeanD(singletonBeanC());
//    }

    @Bean
    @DependsOn(value = {"singletonBeanA"})
    public GetBeanFactoryBean getBeanFactoryBean() {
        return new GetBeanFactoryBean();
    }

}
