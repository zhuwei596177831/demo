package com.example;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-11-06 15:26:52
 * @description
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeComponent1 implements PrototypeInterface {
    public PrototypeComponent1() {
        System.out.println("PrototypeComponent1's constructor......");
    }
}
