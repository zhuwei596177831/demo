package com.example.mianshi.thread.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 朱伟伟
 * @date 2021-03-22 15:14:40
 * @description 死锁时处理方式二：申请锁时限时等待
 */
public class DeadlockTryLockTest implements Runnable {

    private static ReentrantLock reentrantLock1 = new ReentrantLock();
    private static ReentrantLock reentrantLock2 = new ReentrantLock();

    private int lock;

    public DeadlockTryLockTest(int lock) {
        this.lock = lock;
    }

    public static void main(String[] args) {
        DeadlockTryLockTest deadlockTest1 = new DeadlockTryLockTest(1);
        Thread thread1 = new Thread(deadlockTest1);
        thread1.setName("thread-01");
        thread1.start();
        DeadlockTryLockTest deadlockTest2 = new DeadlockTryLockTest(2);
        Thread thread2 = new Thread(deadlockTest2);
        thread2.setName("thread-02");
        thread2.start();
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                reentrantLock1.lock();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (reentrantLock2.tryLock(3, TimeUnit.SECONDS)) {
                    System.out.println("线程：" + Thread.currentThread().getName() + "获取锁reentrantLock2成功");
                } else {
                    System.out.println("线程：" + Thread.currentThread().getName() + "获取锁reentrantLock2失败");
                }
            } else {
                reentrantLock2.lock();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (reentrantLock1.tryLock(3, TimeUnit.SECONDS)) {
                    System.out.println("线程：" + Thread.currentThread().getName() + "获取锁reentrantLock1成功");
                } else {
                    System.out.println("线程：" + Thread.currentThread().getName() + "获取锁reentrantLock1失败");
                }
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
