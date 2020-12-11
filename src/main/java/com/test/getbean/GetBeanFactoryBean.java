package com.test.getbean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author 朱伟伟
 * @date 2020-11-25 10:06:50
 * @description
 */
public class GetBeanFactoryBean implements FactoryBean<FactoryBeanInstance> {
    @Override
    public FactoryBeanInstance getObject() throws Exception {
        return new FactoryBeanInstance();
//        return new FactoryBeanInstanceSubClass();
    }

    @Override
    public Class<?> getObjectType() {
        return FactoryBeanInstance.class;
//        return FactoryBeanInstanceSubClass.class;
    }
}
