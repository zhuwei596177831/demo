package com.example.mianshi.designpatterns.iterator;

/**
 * @author 朱伟伟
 * @date 2021-03-11 09:20:35
 * @description
 */
public interface MyIterator<E> {

    boolean hasNext();

    E next();
}
