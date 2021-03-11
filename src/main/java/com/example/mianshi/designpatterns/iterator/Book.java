package com.example.mianshi.designpatterns.iterator;

/**
 * @author 朱伟伟
 * @date 2021-03-11 09:21:32
 * @description
 */
public class Book {
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                '}';
    }
}
