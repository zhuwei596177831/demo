package com.example.demo.test;

/**
 * @author 朱伟伟
 * @date 2020-12-23 11:22:39
 * @description
 */
public class ThreadLocalEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ThreadLocalEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
