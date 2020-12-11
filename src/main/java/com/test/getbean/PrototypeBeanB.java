package com.test.getbean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-11-26 16:49:29
 * @description
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeBeanB {

    public PrototypeBeanB() {
    }
//    @Autowired
//    PrototypeBeanA prototypeBeanA;
}
