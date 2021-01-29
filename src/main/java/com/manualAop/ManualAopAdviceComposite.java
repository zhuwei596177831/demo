package com.manualAop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * @author 朱伟伟
 * @date 2021-01-29 11:17:00
 * @description
 */
public class ManualAopAdviceComposite implements MethodBeforeAdvice, AfterReturningAdvice, MethodInterceptor, ThrowsAdvice {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("invoke before");
        Object proceed = invocation.proceed();
        System.out.println("invoke after");
        return proceed;
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("afterReturning");
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before");
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-01-29 13:58
     * @description:
     * @see org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor
     **/
    public void afterThrowing(Exception e) {
        System.out.println(e);
    }

}
