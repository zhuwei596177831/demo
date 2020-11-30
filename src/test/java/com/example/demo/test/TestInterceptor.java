package com.example.demo.test;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopContext;

/**
 * @author 朱伟伟
 * @date 2020-11-05 14:02:06
 * @description
 */
public class TestInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(invocation.getMethod().getName() + "TestInterceptor前......");
        invocation.proceed();
        System.out.println("AopContext currentProxy" + AopContext.currentProxy().getClass());
        System.out.println(invocation.getMethod().getName() + "TestInterceptor后......");
        return null;
    }
}
