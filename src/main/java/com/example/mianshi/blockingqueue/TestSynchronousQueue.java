package com.example.mianshi.blockingqueue;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-04-26 11:12:36
 * @description 无缓冲无界队列
 */
public class TestSynchronousQueue {
    private static final BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

    public static void main(String[] args) {
        Runnable producer = () -> {
            for (; ; ) {
                try {
                    String put = UUID.randomUUID().toString();
                    System.out.println(Thread.currentThread().getName() + "生产：" + put);
                    blockingQueue.put(put);
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable consumer = () -> {
            while (true) {
                try {
                    String take = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + "消费：" + take);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
       new Thread(producer).start();
       new Thread(consumer).start();
    }
}
