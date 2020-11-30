package com.example.getbean;

import com.example.demo.PrototypeA;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 朱伟伟
 * @date 2020-11-25 10:09:25
 * @description
 */
public class GetBeanConfigurationTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(GetBeanConfiguration.class);
//        applicationContext.getBean(FactoryBeanInstance.class);
//        applicationContext.getBean("getBeanFactoryBean");

//        applicationContext.getBean(GetBeanFactoryBean.class);
//        applicationContext.getBean(GetBeanFactoryBean.class);
//        applicationContext.getBean(BeanFactory.FACTORY_BEAN_PREFIX + "getBeanFactoryBean");

        applicationContext.getBean(PrototypeBeanA.class);
    }
}
