package com.example.mianshi.blockingqueue;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 朱伟伟
 * @date 2021-04-25 17:55:58
 * @description 添加：
 * add方法在添加元素的时候，若超出了度列的长度会直接抛出异常：
 * offer方法在添加元素时，如果发现队列已满无法添加的话，会直接返回false。
 * 对于put方法，若向队尾添加元素的时候发现队列已经满了会发生阻塞一直等待空间，以加入元素。
 * 移除：
 * poll: 若队列为空，返回null。
 * remove:若队列为空，抛出NoSuchElementException异常。
 * take:若队列为空，发生阻塞，等待有元素。
 */
public class TestPutTake {
//    private static final BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(20);
    private static final BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(10);
    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        Thread producer = new Thread(new Producer());
        producer.setName("生产者-" + atomicInteger.getAndIncrement());
        producer.start();

        Thread consumer = new Thread(new Consumer());
        consumer.setName("消费者-" + atomicInteger.getAndIncrement());
        consumer.start();

    }

    private static class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    String s = UUID.randomUUID().toString();
                    System.out.println(Thread.currentThread().getName() + "生产：" + s);
                    blockingQueue.put(s);
//                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            for (; ; ) {
                try {
                    String take = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + "消费：" + take);
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
