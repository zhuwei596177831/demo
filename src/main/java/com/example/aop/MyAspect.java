package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @author 朱伟伟
 * @date 2020-09-14 10:10:13
 * @description
 */
@Aspect
public class MyAspect {

    @Pointcut(value = "execution(* com.example.aop.UserComponent.*(..))")
//    @Pointcut(value = "execution(* com.example.aop.AopTestInterface.getId(..))")
    public void userComponentPointCut() {
    }

    @Before(value = "userComponentPointCut()")
    public void beforeUserComponent(JoinPoint joinPoint) {
        System.out.println("before " + joinPoint.getSignature().getName() + "......");
    }

    @After(value = "userComponentPointCut()")
    public void afterUserComponent(JoinPoint joinPoint) {
        System.out.println("after " + joinPoint.getSignature().getName() + "......");
    }

    @AfterReturning(pointcut = "userComponentPointCut()", returning = "value")
    public void afterReturningUserComponent(JoinPoint joinPoint, Object value) {
        System.out.println("afterReturning " + joinPoint.getSignature().getName() + "......" + ",returnValue：" + value);
    }

    @AfterThrowing(pointcut = "userComponentPointCut()", throwing = "e")
    public void afterThrowingUserComponent(JoinPoint joinPoint, Exception e) {
        System.out.println("afterThrowing " + joinPoint.getSignature().getName() + "......" + e.getMessage());
    }


    @Pointcut(value = "execution(* com.example.aop.PersonComponent.*(..))")
    public void personComponentPointCut() {

    }

    @Before(value = "personComponentPointCut()")
    public void beforePersonComponent(JoinPoint joinPoint) {
        System.out.println("before " + joinPoint.getSignature().getName() + "......");
    }

    @After(value = "personComponentPointCut()")
    public void afterPersonComponent(JoinPoint joinPoint) {
        System.out.println("after " + joinPoint.getSignature().getName() + "......");
    }

}
