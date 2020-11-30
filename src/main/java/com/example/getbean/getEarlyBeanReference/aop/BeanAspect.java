package com.example.getbean.getEarlyBeanReference.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-11-27 10:39:58
 * @description
 */
@Component
@Aspect
public class BeanAspect {

    @Pointcut(value = "execution(* com.example.getbean.getEarlyBeanReference.EarlyBeanReferenceBeanA.test())")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) {

    }

}
