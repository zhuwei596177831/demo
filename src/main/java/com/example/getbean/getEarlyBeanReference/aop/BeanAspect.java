package com.example.getbean.getEarlyBeanReference.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-11-27 10:39:58
 * @description
 */
@Component
@Aspect
public class BeanAspect {

    @Pointcut(value = "execution(* com.example.getbean.getEarlyBeanReference.EarlyBeanReferenceBeanA.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("before");
        for (Object arg : joinPoint.getArgs()) {
            System.out.println("param:" + arg);
        }
    }

    @After(value = "pointCut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("after");
    }

    @AfterReturning(pointcut = "pointCut()", returning = "returnData")
    public void afterReturning(JoinPoint joinPoint, Object returnData) {
        System.out.println("afterReturning:" + returnData);
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "myException")
    public void afterThrowing(JoinPoint joinPoint, Exception myException) {
        System.out.println("afterThrowing");
    }

    @Around(value = "pointCut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        for (Object arg : proceedingJoinPoint.getArgs()) {
            System.out.println("param:" + arg);
        }
        System.out.println("around");
        Object afterReturning = proceedingJoinPoint.proceed(new Object[]{"朱伟伟"});
        System.out.println("afterReturning:" + afterReturning);
    }

}
