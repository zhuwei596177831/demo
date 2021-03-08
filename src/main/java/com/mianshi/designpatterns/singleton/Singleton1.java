package com.mianshi.designpatterns.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 朱伟伟
 * @date 2021-03-06 12:53:32
 * @description 枚举 线程安全
 */
public enum Singleton1 {
    INSTANCE;

    private final Map<String, String> map = new ConcurrentHashMap<>();

    public void test() {
        System.out.println("test");
    }

    public String add(String key, String value) {
        return map.put(key, value);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public void remove(String key, String value) {
        map.remove(key, value);
    }

}
