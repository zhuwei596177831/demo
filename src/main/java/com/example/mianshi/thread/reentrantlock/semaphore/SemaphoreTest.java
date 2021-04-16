package com.example.mianshi.thread.reentrantlock.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-03-23 09:35:39
 * @description
 */
public class SemaphoreTest {
    /**
     * 指定同时最多5个线程访问共享区域 23-25行
     */
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Runnable runnable = () -> {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + " done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        };
        for (int i = 0; i < 20; i++) {
            executorService.submit(runnable);
        }
    }
}
