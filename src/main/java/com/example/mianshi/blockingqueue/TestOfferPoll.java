package com.example.mianshi.blockingqueue;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 朱伟伟
 * @date 2021-04-26 09:35:45
 * @description
 */
public class TestOfferPoll {
    private static final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(20);
    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        Runnable producer = () -> {
            for (; ; ) {
                try {
                    String PUT = UUID.randomUUID().toString();
                    boolean offer = blockingQueue.offer(PUT);
                    System.out.println(Thread.currentThread().getName() + offer);
                    if (offer) {
                        System.out.println(Thread.currentThread().getName() + "生产：" + PUT);
                    }
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable consumer = () -> {
            for (; ; ) {
                try {
                    String poll = blockingQueue.poll();
                    System.out.println(Thread.currentThread().getName() + "消费：" + poll);
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(producer);
        thread.setName("生产者-" + atomicInteger.getAndIncrement());
        thread.start();

        Thread thread1 = new Thread(consumer);
        thread1.setName("消费者-" + atomicInteger.getAndIncrement());
        thread1.start();
    }
}
