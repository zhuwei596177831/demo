package com.example.mianshi.designpatterns.iterator;

/**
 * @author 朱伟伟
 * @date 2021-03-11 09:24:06
 * @description
 */
public interface MyIterable<E> {
    MyIterator<E> iterator();
}
