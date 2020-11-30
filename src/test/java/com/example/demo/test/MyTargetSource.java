package com.example.demo.test;

/**
 * @author 朱伟伟
 * @date 2020-11-05 10:23:11
 * @description
 */
public class MyTargetSource implements TargetSourceInterface {

    private static MyTargetSource INSTANCE;

    public static MyTargetSource getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (MyTargetSource.class) {
                INSTANCE = new MyTargetSource();
            }
        }
        return INSTANCE;
    }


    public String test(String name) {
        System.out.println(name);
        return name;
    }
}
