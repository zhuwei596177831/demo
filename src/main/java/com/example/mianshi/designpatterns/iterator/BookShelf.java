package com.example.mianshi.designpatterns.iterator;

import java.util.List;

/**
 * @author 朱伟伟
 * @date 2021-03-11 09:22:16
 * @description
 */
public class BookShelf<E> implements MyIterable<E> {

    private final List<E> data;

    public BookShelf(List<E> books) {
        this.data = books;
    }

    public void appendBook(E e) {
        this.data.add(e);
    }

    public int getSize() {
        return this.data.size();
    }

    @Override
    public MyIterator<E> iterator() {
        return new MyBookShelfIterator();
    }

    private class MyBookShelfIterator implements MyIterator<E> {

        private int index;

        @Override
        public boolean hasNext() {
            return index != data.size();
        }

        @Override
        public E next() {
            E e = data.get(index);
            index++;
            return e;
        }
    }

}
