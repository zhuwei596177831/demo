package com.example.mianshi.designpatterns.singleton;

/**
 * @author 朱伟伟
 * @date 2021-03-06 12:46:36
 * @description 线程安全 饿汉式
 */
public class Singleton {

    public static void main(String[] args) {
        System.out.println(STATUS);
    }

    public static int STATUS = 1;

    private static final Singleton SINGLETON = new Singleton();

    private Singleton() {
        System.out.println("Singleton constructor");
    }

    public static Singleton getInstance() {
        return SINGLETON;
    }

    public void test() {
        System.out.println("test");
    }

}
