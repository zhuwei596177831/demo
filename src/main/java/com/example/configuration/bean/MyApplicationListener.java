package com.example.configuration.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.PriorityOrdered;

/**
 * @author 朱伟伟
 * @date 2020-09-08 14:34:43
 * @description
 */
public class MyApplicationListener implements BeanPostProcessor, ApplicationListener, PriorityOrdered {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
