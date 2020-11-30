package com.example.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-09-02 16:03:56
 * @description
 */
@Component
public class TestComponentAwareImpl implements TestComponentAware, ApplicationContextAware {

    private TestComponentAware testComponentAware;
    private ApplicationContext applicationContext;

    @Override
    @Autowired
    public void setTestComponentAware(TestComponentAware testComponentAware) {
        this.testComponentAware = testComponentAware;
    }

    @Override
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
