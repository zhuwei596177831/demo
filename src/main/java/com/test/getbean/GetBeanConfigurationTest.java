package com.test.getbean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 朱伟伟
 * @date 2020-11-25 10:09:25
 * @description
 */
public class GetBeanConfigurationTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(GetBeanConfiguration.class);
//        System.out.println(applicationContext.getBean(FactoryBeanInstance.class));
//        System.out.println(applicationContext.getBean("getBeanFactoryBean"));
//
//        System.out.println(applicationContext.getBean(GetBeanFactoryBean.class));
//        System.out.println(applicationContext.getBean(GetBeanFactoryBean.class));
//        System.out.println(applicationContext.getBean(BeanFactory.FACTORY_BEAN_PREFIX + "getBeanFactoryBean"));

//        applicationContext.getBean(PrototypeBeanA.class);
    }
}
