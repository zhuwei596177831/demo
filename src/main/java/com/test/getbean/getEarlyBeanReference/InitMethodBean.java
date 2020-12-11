package com.test.getbean.getEarlyBeanReference;

/**
 * @author 朱伟伟
 * @date 2020-12-01 10:57:41
 * @description
 */
public class InitMethodBean {

    public void initMethod() {
        System.out.println("InitMethodBean initMethod......");
    }

    public void destroyMethod() {
        System.out.println("InitMethodBean destroyMethod......");
    }

}
