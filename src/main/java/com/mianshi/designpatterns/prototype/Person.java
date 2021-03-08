package com.mianshi.designpatterns.prototype;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author 朱伟伟
 * @date 2021-03-08 17:13:07
 * @description
 */
public class Person implements Cloneable {
    private String name;
    private String desc;

    public Person(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    @Override
    protected Object clone() {
        Person person = null;

        try {
            person = (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return person;
    }
}
