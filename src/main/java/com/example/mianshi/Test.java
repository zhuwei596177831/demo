package com.example.mianshi;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 朱伟伟
 * @date 2021-03-10 17:39:10
 * @description
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer == 2)
//                list.add(5);
            iterator.remove();
//            list.remove(integer);
        }
    }
}
