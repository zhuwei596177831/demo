package com.example.mianshi.thread.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 朱伟伟
 * @date 2021-03-22 15:14:40
 * @description 死锁时处理方式一：中断响应
 */
public class DeadlockInterruptTest implements Runnable {

    private static ReentrantLock reentrantLock1 = new ReentrantLock();
    private static ReentrantLock reentrantLock2 = new ReentrantLock();

    private int lock;

    public DeadlockInterruptTest(int lock) {
        this.lock = lock;
    }

    public static void main(String[] args) {
        DeadlockInterruptTest deadlockTest1 = new DeadlockInterruptTest(1);
        Thread thread1 = new Thread(deadlockTest1);
        thread1.start();
        DeadlockInterruptTest deadlockTest2 = new DeadlockInterruptTest(2);
        Thread thread2 = new Thread(deadlockTest2);
        thread2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();

    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                //尝试获取锁时可以被中断
                reentrantLock1.lockInterruptibly();
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock2.lockInterruptibly();
            } else {
                reentrantLock2.lockInterruptibly();
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reentrantLock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
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
