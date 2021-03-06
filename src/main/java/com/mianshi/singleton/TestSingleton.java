package com.mianshi.singleton;

/**
 * @author 朱伟伟
 * @date 2021-03-06 12:54:05
 * @description
 */
public class TestSingleton {
    public static void main(String[] args) {
        Singleton.getInstance().test();
        Singleton1.INSTANCE.test();
        Singleton1.INSTANCE.add("ss", "dd");
    }
}
