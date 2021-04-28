package com.example.mianshi.volatileTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-04-28 10:32:43
 * @description volatile List测试
 */
public class VolatileListTest1 {

    private static volatile List<Integer> list = new ArrayList<>(2);

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    static {
        list.add(0);
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
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.set(1, 1);
        }).start();

        new Thread(() -> {
            List<Integer> integers;
//            while ((integers = list).get(0) == 0) {
//            }
            while (list.get(0) == 0) {
            }
            System.out.println("end");
//            while ((integers).get(1) == 0) {
//            }
            while (list.get(1) == 0) {

            }
            countDownLatch.countDown();
        }).start();

        countDownLatch.await();
    }
}
