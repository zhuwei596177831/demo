package com.example;

/**
 * @author 朱伟伟
 * @date 2020-09-24 16:57:59
 * @description
 */
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
