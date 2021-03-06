package com.test.getbean.getEarlyBeanReference.aop;

/**
 * @author 朱伟伟
 * @date 2020-12-01 17:22:59
 * @description
 */
public interface TestJdkProxy {
    String test(String name);

    void currentProxyMethod(String param);

    /**
     * @author: 朱伟伟
     * @date: 2020-12-07 11:09
     * @description: 循环依赖时 async测试
     **/
    void earlyReferenceAsyncMethod();
}
