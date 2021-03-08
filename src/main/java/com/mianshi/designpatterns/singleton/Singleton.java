package com.mianshi.designpatterns.singleton;

/**
 * @author 朱伟伟
 * @date 2021-03-06 12:46:36
 * @description 线程安全 饿汉式
 */
public class Singleton {

    private static final Singleton SINGLETON = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return SINGLETON;
    }

    public void test() {
        System.out.println("test");
    }

}
