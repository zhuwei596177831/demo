package com.example.demo.test;

import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

/**
 * @author 朱伟伟
 * @date 2020-11-05 18:32:12
 * @description
 */
public class PersonServiceTest {
    @Test
    public void test() {
        ProxyFactory proxyFactory = new ProxyFactory();
//        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addInterface(PersonService.class);
        proxyFactory.setTarget(new PersonServiceImpl());
        proxyFactory.addAdvice(new TestInterceptor());
        PersonService personService = (PersonService) proxyFactory.getProxy();
        personService.hello();
    }
}
