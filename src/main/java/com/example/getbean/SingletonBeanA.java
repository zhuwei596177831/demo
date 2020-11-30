package com.example.getbean;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 朱伟伟
 * @date 2020-11-25 10:05:36
 * @description
 */
public class SingletonBeanA {
    @Autowired
    private SingletonBeanB beanB;
}
