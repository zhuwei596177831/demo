package com.test.getbean.getEarlyBeanReference.aop;

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

    //    @Pointcut(value = "execution(* com.test.getbean.getEarlyBeanReference.EarlyBeanReferenceBeanA.test(..))")
    @Pointcut(value = "execution(* com.test.getbean.getEarlyBeanReference.aop.TestJdkProxy.test(..))")
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
        System.out.println("returnData:" + returnData);
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
        Object afterReturning = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        System.out.println("afterReturning:" + afterReturning);
    }

    @Pointcut(value = "execution(* com.test.getbean.getEarlyBeanReference.aop.AopServiceImpl.*(..))")
    public void aopServicePointCut() {
    }

    @Before(value = "aopServicePointCut()")
    public void beforeAopService(JoinPoint joinPoint) {

    }

    @Pointcut(value = "execution(* com.test.getbean.getEarlyBeanReference.transaction.propagation.OneService.*(..))")
    public void oneServicePointCut() {
    }

    @Before(value = "oneServicePointCut()")
    public void beforeOneService(JoinPoint joinPoint) {
        System.out.println("beforeOneService");
    }

}
