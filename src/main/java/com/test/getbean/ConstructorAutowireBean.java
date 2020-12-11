package com.test.getbean;

import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-11-26 15:42:25
 * @description
 */
@Component
public class ConstructorAutowireBean {

    private SingletonBeanA beanA;

    public ConstructorAutowireBean(SingletonBeanA beanA, CommonBean commonBean) {
        this.beanA = beanA;
    }

    public SingletonBeanA getBeanA() {
        return beanA;
    }

    public void setBeanA(SingletonBeanA beanA) {
        this.beanA = beanA;
    }
}
