package com.example.configuration;

import com.example.configuration.bean.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 朱伟伟
 * @date 2020-09-03 16:37:23
 * @description
 */
@Configuration
@ConditionalOnClass(value = {String.class})
//@Configuration(proxyBeanMethods = false)
//@Component
@ComponentScan(basePackages = {"com.example.configuration.scan"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, value = {MyCustomFilterType.class})
})
@Import({TestImportBean.class, TestImportSelector.class, TestImportBeanDefinitionRegistrar.class, ImportConfiguration.class})
//@ImportResource
//@PropertySource(value = "classpath:test.properties")
public class TestConfiguration {

//    private ApplicationContext applicationContext;
//
//    public TestConfiguration(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }
//
//    @Resource
//    private TestImportBean testImportBean;

    @Bean
    public MethodBean methodBean() {
        System.out.println("注入methodBean");
        return new MethodBean();
    }

    @Bean
    public BeanMethod beanMethod() {
        methodBean();
        return new BeanMethod();
    }

//    @Bean
//    public MyApplicationListener myApplicationListener() {
//        return new MyApplicationListener();
//    }

    @Bean
    public MyFactoryBean myFactoryBean() {
        return new MyFactoryBean();
    }

    @Configuration(proxyBeanMethods = false)
    static class InnerClassConfiguration {
        @Bean
        public InnerClassConfigurationBean innerClassConfigurationBean() {
            return new InnerClassConfigurationBean();
        }
    }


}
