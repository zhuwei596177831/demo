package com.example.mianshi.thread.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 朱伟伟
 * @date 2021-03-22 16:29:19
 * @description 公平锁
 */
public class FairReentrantLock {

    private static ReentrantLock reentrantLock = new ReentrantLock(true);
//    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable runnable = () -> {
            while (true) {
                try {
                    reentrantLock.lock();
                    System.out.println(Thread.currentThread().getName() + "获得锁");
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        thread1.setName("thread-01");
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.setName("thread-02");
        thread2.start();

    }

}
