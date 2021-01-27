package com.test.getbean.getEarlyBeanReference.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-12-03 16:03:11
 * @description
 */
//@Component
//@Aspect
//@Order
public class TransactionTestServiceOrderAspect {

    @Pointcut(value = "execution(* com.test.getbean.getEarlyBeanReference.transaction.TransactionTestService.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void orderBefore(JoinPoint joinPoint) {
        System.out.println("TransactionTestServiceAspect before");
    }
}
