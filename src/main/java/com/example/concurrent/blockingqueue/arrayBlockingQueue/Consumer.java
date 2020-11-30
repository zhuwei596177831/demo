package com.example.concurrent.blockingqueue.arrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * @author 朱伟伟
 * @date 2020-10-23 17:28:10
 * @description
 */
public class Consumer implements Runnable {

    private final BlockingQueue<Integer> blockingQueue;

    public Consumer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
