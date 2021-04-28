package com.example.mianshi.volatileTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-04-28 10:32:43
 * @description volatile List测试
 * 从Java5开始，Volatile保证的已经不仅仅是被修饰的变量了，而是所有变量。
 * <p>
 * 读取一个Volatile变量时，会去主存中读取该变量的值，同时也会将在该行为之后的变量的值一并从主存中拿取
 * 写入一个Volatile变量时，会将该操作之前发生的修改统统刷新到主存中去。
 */
public class VolatileListTest {
//    private static List<Integer> list = new ArrayList<>(2);

    private static List<Integer> list = new ArrayList<>(2);
    private static volatile boolean flag;

//    private static volatile List<Integer> list = new ArrayList<>(2);

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    static {
        list.add(0);
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.set(0, 1);
            System.out.println("change data 0 to 1");
        }).start();

        new Thread(() -> {
            while (list.get(0) == 0) {
                boolean f = flag;
            }
            countDownLatch.countDown();
            System.out.println("end");
        }).start();

        countDownLatch.await();
    }
}
