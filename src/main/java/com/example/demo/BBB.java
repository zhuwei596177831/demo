package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-08-21 16:31:14
 * @description
 */
@Component
public class BBB {
    @Autowired
    AAA aaa;
    @Value("${server.port}")
    private String port;

    public void test() {

    }

}
