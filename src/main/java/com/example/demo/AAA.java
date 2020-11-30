package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-08-21 16:31:03
 * @description
 */
@Component
@Primary
@Lazy(value = false)
public class AAA {
    @Autowired
    BBB bbb;
    @Autowired
//    @Lazy
    RequestComponent requestComponent;
    @Autowired
    PrototypeA prototypeA;
    @Autowired
    @Qualifier(value = "qualifierInterfaceImpl")
    QualifierInterface qualifierInterface;
    @Value("${server.port}")
    private String port;

    public void hello() {
        System.out.println(requestComponent);
    }

}
