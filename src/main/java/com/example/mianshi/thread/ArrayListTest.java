package com.example.mianshi.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 朱伟伟
 * @date 2021-04-16 09:55:46
 * @description 多线程下的list
 */
public class ArrayListTest {

    //    static List<Integer> list = new ArrayList<>();
    static List<Integer> list = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws InterruptedException {

        Runnable runnable = () -> {
            for (int i = 0; i < 1000000; i++) {
                list.add(i);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Thread thread1 = new Thread(runnable);
        thread1.start();


        thread.join();
        thread1.join();

        System.out.println(list.size());

    }
}
