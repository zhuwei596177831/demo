package com.example.mianshi.thread.reentrantlock.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 朱伟伟
 * @date 2021-03-22 16:50:23
 * @description 线程通信
 * 相似：
 * Object wait() notify()
 */
public class ReentrantLockContidion {
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static Condition condition = reentrantLock.newCondition();

    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
//                reentrantLock.lock();
                if (reentrantLock.tryLock()) {
                    System.out.println(Thread.currentThread().getName() + " get lock success");
                } else {
                    System.out.println(Thread.currentThread().getName() + " get lock fail");
                }
                condition.await();
                System.out.println(Thread.currentThread().getName() + "going on...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        };

        Thread thread = new Thread(runnable);
        thread.setName("thread-01");
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (reentrantLock.tryLock()) {
            System.out.println(Thread.currentThread().getName() + " get lock success");
        } else {
            System.out.println(Thread.currentThread().getName() + " get lock fail");
        }

        //唤醒
        condition.signal();
        //释放锁
        reentrantLock.unlock();
    }

}
