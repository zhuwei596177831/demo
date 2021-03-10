package com.example.mianshi.designpatterns.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author 朱伟伟
 * @date 2021-03-10 15:16:52
 * @description
 */
public class MyMapperFactoryBean<T> implements FactoryBean<T> {

    private final Logger logger = LoggerFactory.getLogger(MyMapperFactoryBean.class);

    private Class<T> mapperInterface;

    public MyMapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            MySelect mySelect = method.getAnnotation(MySelect.class);
            if (mySelect != null) {
                logger.info("SQL:{}", mySelect.value()[0]);
            }
            return Arrays.toString(args) + "zww";
        };
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperInterface}, invocationHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }
}
