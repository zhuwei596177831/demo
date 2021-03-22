package com.example.mianshi.thread.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 朱伟伟
 * @date 2021-03-22 14:57:03
 * @description 重入锁
 * {@link java.util.concurrent.locks.ReentrantLock}
 */
public class ReentrantLockTest {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static int i = 0;

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> {
            for (int i1 = 0; i1 < 10000; i1++) {
                reentrantLock.lock();
                try {
                    i++;
                } finally {
                    reentrantLock.unlock();
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            for (int i1 = 0; i1 < 10000; i1++) {
                reentrantLock.lock();
                try {
                    i++;
                } finally {
                    reentrantLock.unlock();
                }
            }
        });
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(i);
    }

}
