package com.test.getbean.getEarlyBeanReference;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-12-02 15:06:24
 * @description
 */
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class PrototypeEarlyBean {

    public PrototypeEarlyBean() {
        System.out.println("PrototypeEarlyBean:" + this);
    }

}
