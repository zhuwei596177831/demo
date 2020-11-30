package com.example.demo.test;

import org.aspectj.weaver.loadtime.definition.Definition;
import org.junit.jupiter.api.Test;
import org.springframework.aop.*;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.ThrowsAdviceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * @author 朱伟伟
 * @date 2020-11-05 10:23:42
 * @description
 */
public class TargetSourceTest {
    @Test
    public void test() {
        TargetSource targetSource = new TargetSource() {
            @Override
            public Class<?> getTargetClass() {
                System.out.println("getTargetClass......");
//                return TargetSourceInterface.class;
                return MyTargetSource.class;
            }

            @Override
            public boolean isStatic() {
                System.out.println("isStatic......");
                return false;
            }

            @Override
            public Object getTarget() throws Exception {
                System.out.println("getTarget......");
                return MyTargetSource.getINSTANCE();
//                return new MyTargetSource();
            }

            @Override
            public void releaseTarget(Object target) throws Exception {
                System.out.println("releaseTarget" + target.getClass().getName());
            }
        };
        ProxyFactory proxyFactory = new ProxyFactory();
        //
        proxyFactory.setExposeProxy(true);
        proxyFactory.setTargetSource(targetSource);
        proxyFactory.addInterface(TargetSourceInterface.class);
        proxyFactory.addAdvice(new TestInterceptor());
        MethodBeforeAdvice methodBeforeAdvice = new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("methodBeforeAdvice " + method.getName() + "," + args + "," + target.getClass().getName());
            }
        };
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(Pointcut.TRUE, methodBeforeAdvice));
        AfterReturningAdvice afterReturningAdvice = new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
                System.out.println("afterReturningAdvice " + method.getName() + "," + args + "," + target.getClass().getName());
            }
        };
        proxyFactory.addAdvice(afterReturningAdvice);
        Object proxy = proxyFactory.getProxy(TargetSourceTest.class.getClassLoader());
//        System.out.println("proxy......" + proxy);
//        System.out.println(proxy.getClass().getName());
        if (proxy instanceof TargetSourceInterface) {
            TargetSourceInterface targetSourceInterface = (TargetSourceInterface) proxy;
            targetSourceInterface.test("朱伟伟");
        }
    }
}
