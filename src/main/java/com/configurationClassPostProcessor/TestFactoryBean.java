package com.configurationClassPostProcessor;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2021-02-04 17:47:20
 * @description
 */
//@Component
public class TestFactoryBean implements FactoryBean<TestFactoryBeanBean> {
    @Override
    public TestFactoryBeanBean getObject() throws Exception {
        return new TestFactoryBeanBean();
    }

    @Override
    public Class<?> getObjectType() {
        return TestFactoryBeanBean.class;
    }
}
