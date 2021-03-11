package com.example.mianshi.designpatterns.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 朱伟伟
 * @date 2021-03-11 09:29:33
 * @description
 */
public class IteratorTest {
    public static void main(String[] args) {
        BookShelf<Book> bookShelf = new BookShelf<>(Arrays.asList(new Book("111"), new Book("222"), new Book("333")));
        MyIterator<Book> iterator = bookShelf.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            System.out.println(book);
        }
    }
}
