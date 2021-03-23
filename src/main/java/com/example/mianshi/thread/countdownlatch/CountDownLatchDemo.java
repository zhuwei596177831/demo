package com.example.mianshi.thread.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-03-23 17:33:43
 * @description 倒计数器
 * {@link java.util.concurrent.CountDownLatch}
 * 倒计时功能 让某一线程等待直到倒计时结束 继续工作
 */
public class CountDownLatchDemo {

    /**
     * count the number of times {@link CountDownLatch#countDown()} must be invoked before threads can pass through {@link CountDownLatch#await()}
     */
    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("going on ......");
    }

}
