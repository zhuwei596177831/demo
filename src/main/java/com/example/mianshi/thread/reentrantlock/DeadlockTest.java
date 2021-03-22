package com.example.mianshi.thread.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 朱伟伟
 * @date 2021-03-22 15:14:40
 * @description 死锁演示
 */
public class DeadlockTest implements Runnable {
    /**
     * ReentrantLock必须是static 公用的
     */
    private static ReentrantLock reentrantLock1 = new ReentrantLock();
    private static ReentrantLock reentrantLock2 = new ReentrantLock();

    private int lock;

    public DeadlockTest(int lock) {
        this.lock = lock;
    }

    public static void main(String[] args) {
        DeadlockTest deadlockTest1 = new DeadlockTest(1);
        Thread thread1 = new Thread(deadlockTest1);
        thread1.start();
        DeadlockTest deadlockTest2 = new DeadlockTest(2);
        Thread thread2 = new Thread(deadlockTest2);
        thread2.start();
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                reentrantLock1.lock();
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock2.lock();
            } else {
                reentrantLock2.lock();
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock1.lock();
            }
        } finally {
            if (reentrantLock1.isHeldByCurrentThread()) {
                System.out.println("reentrantLock1 unlock");
                reentrantLock1.unlock();
            }
            if (reentrantLock2.isHeldByCurrentThread()) {
                System.out.println("reentrantLock2 unlock");
                reentrantLock2.unlock();
            }
            System.out.println("线程：" + Thread.currentThread().getName() + "退出");
        }
    }
}
