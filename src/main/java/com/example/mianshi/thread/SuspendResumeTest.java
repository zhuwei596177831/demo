package com.example.mianshi.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-03-26 10:43:24
 * @description 线程的挂起与继续执行
 */
public class SuspendResumeTest {

    private static final Object monitor = new Object();

    public static void main(String[] args) {
        Runnable runnable = () -> {
            synchronized (monitor) {
                System.out.println(Thread.currentThread().getName());
                Thread.currentThread().suspend();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.resume();
        thread1.resume();
    }

}
