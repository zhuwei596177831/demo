package com.example.mianshi.designpatterns.singleton;

/**
 * @author 朱伟伟
 * @date 2021-03-06 13:03:43
 * @description 懒汉式
 */
public class Singleton2 {
    //volatile禁止指令重排
    private static volatile Singleton2 INSTANCE;

    private Singleton2() {
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-03-06 13:06
     * @description: 线程不安全
     **/
    public static Singleton2 getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (Singleton2.class) {
                INSTANCE = new Singleton2();
            }
        }
        return INSTANCE;
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-03-06 13:07
     * @description: 线程安全
     **/
    public static synchronized Singleton2 getINSTANCE1() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-03-06 13:07
     * @description: 线程安全
     **/
    public static Singleton2 getINSTANCE2() {
        if (INSTANCE == null) {
            synchronized (Singleton2.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton2();
                }
            }
        }
        return INSTANCE;
    }


    public void test() {
        System.out.println("test");
    }

}
