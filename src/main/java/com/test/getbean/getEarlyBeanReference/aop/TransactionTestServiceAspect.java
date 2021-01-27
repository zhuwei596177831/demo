package com.test.getbean.getEarlyBeanReference.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-12-03 16:03:11
 * @description
 */
@Component
@Aspect
public class TransactionTestServiceAspect {

    @Pointcut(value = "execution(* com.test.getbean.getEarlyBeanReference.transaction.TransactionTestService.getList(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
    }

    @After(value = "pointCut()")
    public void after(JoinPoint joinPoint) {
    }

    @AfterReturning(pointcut = "pointCut()", returning = "returnData")
    public void afterReturning(JoinPoint joinPoint, Object returnData) {
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "myException")
    public void afterThrowing(JoinPoint joinPoint, Exception myException) {
    }

}
