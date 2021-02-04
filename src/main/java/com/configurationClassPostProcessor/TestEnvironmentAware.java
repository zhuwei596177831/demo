package com.configurationClassPostProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author 朱伟伟
 * @date 2021-02-04 16:33:51
 * @description
 */
@Component
public class TestEnvironmentAware implements EnvironmentAware {

    private Environment awareEnvironment;

    @Autowired
    private Environment autowiredEnvironment;

    @Override
    public void setEnvironment(Environment environment) {
        this.awareEnvironment = environment;
    }

    @PostConstruct
    public void init() {
//        System.out.println("autowiredEnvironment:" + autowiredEnvironment);
//        System.out.println("awareEnvironment:" + awareEnvironment);
    }

}
