package com.example.mianshi.blockingqueue;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-04-26 16:32:16
 * @description
 */
public class BlockingQueueTest {
    private static final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {

        Runnable producer = () -> {
            for (; ; ) {
                try {
                    String s = UUID.randomUUID().toString();
                    System.out.println(Thread.currentThread().getName() + "生产：" + s);
                    blockingQueue.put(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(producer, "producer");
        thread.start();
        TimeUnit.SECONDS.sleep(5);

        Runnable consumer = () -> {
            for (; ; ) {
                try {
                    String take = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + "消费：" + take);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread1 = new Thread(consumer, "consumer01");
        thread1.start();

//        Thread thread2 = new Thread(consumer,"consumer02");
//        thread2.start();

    }
}
