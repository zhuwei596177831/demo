package com.example.mianshi.thread.reentrantlock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 朱伟伟
 * @date 2021-03-22 17:03:23
 * @description
 */
public class ConditionLockTicket {

    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static Condition condition = reentrantLock.newCondition();
    private static int ticket = 100;

    public static void main(String[] args) {
        Runnable runnable = () -> {
            do {
                try {
                    reentrantLock.lock();
                    condition.signal();
                    System.out.println(Thread.currentThread().getName() + " sell ticket " + ticket--);
                    condition.await();
                    reentrantLock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (ticket > 0);
        };
        Thread thread1 = new Thread(runnable);
        thread1.setName("thread-01");
        thread1.start();

        Thread thread2 = new Thread(runnable);
        thread2.setName("thread-02");
        thread2.start();
    }

}
