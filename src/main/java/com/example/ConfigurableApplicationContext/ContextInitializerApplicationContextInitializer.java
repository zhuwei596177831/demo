package com.example.ConfigurableApplicationContext;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 朱伟伟
 * @date 2020-11-03 13:29:06
 * @description
 */
public class ContextInitializerApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("ContextInitializerApplicationContextInitializer........");
    }
}
