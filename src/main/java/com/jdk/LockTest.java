package com.jdk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 朱伟伟
 * @date 2021-01-28 10:17:10
 * @description
 */
public class LockTest {
    //可重入锁
    private static final Lock lock = new ReentrantLock();
    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
//         Thread thread = new Thread(() -> {
////            lockTest.insert(Thread.currentThread());
////        });
////        thread.start();
//        Thread thread1 = new Thread(() -> {
//            lockTest.insert(Thread.currentThread());
//        });
//        thread1.start();


//        Thread thread = new Thread(LockTest::insert1);
//        thread.start();
//
//        Thread thread1 = new Thread(LockTest::insert1);
//        thread1.start();


//        testReentrantLock();

        Ticket ticket = new Ticket();
        Thread sellThread = new Thread(ticket, "thread1");
        sellThread.start();
        Thread sellThread1 = new Thread(ticket, "thread2");
        sellThread1.start();
    }

    static class Ticket implements Runnable {
        private int num = 100;
        private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private final Object monitor = new Object();

        @Override
        public void run() {
            while (true) {
                try {
                    readWriteLock.writeLock().lock();
                    if (num > 0) {
                        System.out.println(Thread.currentThread().getName() + " sell ticket " + num--);
                    } else {
                        break;
                    }
                } finally {
                    readWriteLock.writeLock().unlock();
                }
            }

//            while (true) {
////                synchronized (this) {
//                synchronized (monitor) {
//                    monitor.notify();
//                    if (num > 0) {
//                        System.out.println(Thread.currentThread().getName() + " sell ticket " + num--);
//                        try {
//                            monitor.wait(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        break;
//                    }
//                }
//            }

        }
    }

    private static void testReentrantLock() {
        LockTest lockTest = new LockTest();
        new Thread(lockTest::syncMethod1).start();
        new Thread(lockTest::syncMethod1).start();
    }

    private synchronized void syncMethod1() {
        System.out.println(Thread.currentThread().getName() + "syncMethod1");
        syncMethod2();
    }

    private synchronized void syncMethod2() {
        System.out.println(Thread.currentThread().getName() + "syncMethod2");
    }


    private static void insert1() {
        if (lock.tryLock()) {
            System.out.println(Thread.currentThread().getName() + "get lock success");
            lock.lock();
            try {
                for (int i = 0; i < 5; i++) {
                    list.add(i);
                }
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "release lock");
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "get lock failed");
        }
    }


    private void insert(Thread thread) {
        if (lock.tryLock()) {
            System.out.println(thread.getName() + "get lock success");
            lock.lock();
            try {
                for (int i = 0; i < 5; i++) {
                    list.add(i);
                }
            } finally {
                lock.unlock();
                System.out.println(thread.getName() + "release lock");
            }
        } else {
            System.out.println(thread.getName() + "get lock failed");
        }
    }


}
