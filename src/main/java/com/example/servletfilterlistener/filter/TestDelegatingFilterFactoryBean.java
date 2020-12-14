package com.example.servletfilterlistener.filter;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

/**
 * @author 朱伟伟
 * @date 2020-12-14 18:46:44
 * @description
 */
public class TestDelegatingFilterFactoryBean implements FactoryBean<Filter> {
    @Override
    public Filter getObject() throws Exception {
        return new TestDelegatingFilter();
    }

    @Override
    public Class<?> getObjectType() {
        return TestDelegatingFilter.class;
    }
}
