package com.example.getbean.getEarlyBeanReference;

import org.springframework.context.annotation.*;

/**
 * @author 朱伟伟
 * @date 2020-11-26 17:16:02
 * @description
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackageClasses = {GetEarlyBeanReferenceConfig.class})
@EnableAspectJAutoProxy
@PropertySource(value = {"classpath:recources.properties"})
public class GetEarlyBeanReferenceConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(GetEarlyBeanReferenceConfig.class);
    }

}
