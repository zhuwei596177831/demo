package com.example.mianshi.designpatterns.singleton;

/**
 * @author 朱伟伟
 * @date 2021-03-06 13:11:40
 * @description 内部类方式 线程安全
 */
public class Singleton3 {

    public static int STATUS;

    private Singleton3() {
        System.out.println("Singleton3 constructor");
    }

    private static class SingletonHolder {
        private static Singleton3 INSTANCE = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
