package com.example.mianshi.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-03-16 10:21:47
 * @description
 */
public class ThreadTerminated {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("terminated");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println("开始执行业务");
//                Thread.yield();
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
    }
}
