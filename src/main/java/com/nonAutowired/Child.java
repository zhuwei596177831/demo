package com.nonAutowired;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 朱伟伟
 * @date 2021-01-04 10:03:34
 * @description
 */
public class Child {

    public HelloService helloService;

    public HelloService getHelloService() {
        return helloService;
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.nonAutowired");
//        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
//        Child child = (Child) autowireCapableBeanFactory.createBean(Child.class, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
//        Child child = autowireCapableBeanFactory.createBean(Child.class);//null
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
//        Child child = (Child) beanFactory.createBean(Child.class, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
        Child child = (Child) beanFactory.createBean(Child.class, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
//        Child child = beanFactory.createBean(Child.class);//null
        System.out.println(child.getHelloService());
    }
}
