package com.configurationClassPostProcessor;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2021-02-03 15:39:56
 * @description
 */
@Component
public class ComponentScanBean {
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    @Autowired
    ImportClass importClass;


    public ComponentScanBean() {
        System.out.println("ComponentScanBean 空参构造器");
    }

    //    @Autowired(required = false)
    public ComponentScanBean(ApplicationContext applicationContext, @Autowired(required = false) TestAuBean testAuBean) {
        System.out.println("ComponentScanBean 单参构造器");
        this.applicationContext = applicationContext;
    }

    @Autowired
    public ComponentScanBean(ApplicationContext applicationContext, BeanFactory beanFactory) {
        System.out.println("ComponentScanBean 多参构造器");
        this.applicationContext = applicationContext;
        this.beanFactory = beanFactory;
    }


    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
