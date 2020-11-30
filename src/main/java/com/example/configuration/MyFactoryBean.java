package com.example.configuration;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author 朱伟伟
 * @date 2020-09-08 15:59:14
 * @description
 */
public class MyFactoryBean implements FactoryBean<EEE> {
    @Override
    public EEE getObject() throws Exception {
        return new EEE();
    }

    @Override
    public Class<?> getObjectType() {
        return EEE.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
