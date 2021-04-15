package com.example.mianshi.thread.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author 朱伟伟
 * @date 2021-03-26 10:48:19
 * @description 线程阻塞工具 {@link java.util.concurrent.locks.LockSupport}
 * <p>
 * {@link Thread#suspend()}
 * {@link Thread#resume()}
 */
public class LockSupportTest {

    private static Object monitor = new Object();


    public static void main(String[] args) {
        Runnable runnable = () -> {
            synchronized (monitor) {
                System.out.println(Thread.currentThread().getName());
                //阻塞当前线程
                LockSupport.park();
                System.out.println(Thread.currentThread().getName()+" going on....");
                System.out.println();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Thread thread1 = new Thread(runnable);
        thread1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(thread);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LockSupport.unpark(thread1);

    }

}
