package com.example.mianshi.blockingqueue;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author 朱伟伟
 * @date 2021-04-26 09:44:22
 * @description 无界队列
 * 成产不会被阻塞
 * {@link DelayQueue#put(Delayed)}
 * {@link DelayQueue#add(Delayed)}
 * {@link DelayQueue#offer(Delayed)}
 * 消费
 * {@link DelayQueue#take()}阻塞
 * {@link DelayQueue#poll()}不阻塞
 */
public class TestDelayQueue {
    private static final BlockingQueue<MyDelayed> blockingQueue = new DelayQueue<>();
    private static final LongAdder longAdder = new LongAdder();

    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        Runnable producer = () -> {
            for (; ; ) {
                try {
                    long value = longAdder.longValue();
                    longAdder.increment();
                    MyDelayed myDelayed = new MyDelayed("myDelayer-" + value, new Random().nextInt(10));
                    System.out.println(Thread.currentThread().getName() + "生产：" + myDelayed);
                    blockingQueue.put(myDelayed);
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable consumer = () -> {
            while (true) {
                try {
                    MyDelayed myDelayed = blockingQueue.take();
//                    MyDelayed myDelayed = blockingQueue.poll();
                    System.out.println(Thread.currentThread().getName() + "消费：" + myDelayed);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        executorService.execute(producer);
        executorService.execute(consumer);

    }
}
