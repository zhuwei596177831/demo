package com.example.demo.test;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2020-12-23 11:22:16
 * @description
 */
public class ThreadLocalTest {
    //    private static final ThreadLocal<ThreadLocalEntity> THREAD_LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<ThreadLocalEntity> THREAD_LOCAL = new InheritableThreadLocal<>();
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(3);

    @Test
    private static void function() throws InterruptedException {
        ThreadLocalEntity threadLocalEntity = new ThreadLocalEntity();
        threadLocalEntity.setName("zww");
        setData(threadLocalEntity);
        new Thread(() -> {
            System.out.println(System.identityHashCode(THREAD_LOCAL.get()));
        }).start();
        new Thread(() -> {
            System.out.println(System.identityHashCode(THREAD_LOCAL.get()));
        }).start();
        TimeUnit.SECONDS.sleep(2);
        new Thread(() -> {
            System.out.println(System.identityHashCode(THREAD_LOCAL.get()));
        }).start();
        System.out.println("======== Finish =========");
    }

    public static void main(String[] args) throws InterruptedException {
//        ThreadLocalEntity threadLocalEntity = new ThreadLocalEntity();
//        threadLocalEntity.setName("zww");
//        setData(threadLocalEntity);
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + "：" + THREAD_LOCAL.get());
//        }).start();
//        EXECUTOR_SERVICE.execute(() -> {
//            System.out.println(Thread.currentThread().getName() + "：" + THREAD_LOCAL.get());
//        });
//        Thread thread1 = new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + "：" + THREAD_LOCAL.get());
//            ThreadLocalEntity threadLocalEntity1 = THREAD_LOCAL.get();
//            if (threadLocalEntity1 != null) {
//                threadLocalEntity1.setName("sss");
//            }
//            setData(threadLocalEntity1);
//        });
//        thread1.start();
//        thread1.join();
//
//        Thread thread2 = new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + "：" + THREAD_LOCAL.get());
//        });
//        thread2.start();
//        thread2.join();
//
//        System.out.println(Thread.currentThread().getName() + "：" + getData());

        function();
    }

    @Test
    private static void setData(ThreadLocalEntity data) {
        THREAD_LOCAL.remove();
        System.out.println("thread：" + Thread.currentThread().getName() + "，setData：" + data);
        THREAD_LOCAL.set(data);
    }

    @Test
    private static ThreadLocalEntity getData() {
        return THREAD_LOCAL.get();
    }
}
