package com.example.mianshi.thradpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-03-26 16:17:31
 * @description
 */
public class FixedPool {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(System.currentTimeMillis()/1000 + " Thread ID：" + Thread.currentThread().getId());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < 20; i++) {
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }
}
