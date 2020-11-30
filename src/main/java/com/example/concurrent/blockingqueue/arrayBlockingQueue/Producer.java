package com.example.concurrent.blockingqueue.arrayBlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * @author 朱伟伟
 * @date 2020-10-23 17:26:13
 * @description
 */
public class Producer implements Runnable {
    private final BlockingQueue<Integer> blockingQueue;

    public Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            blockingQueue.put(1);
            Thread.sleep(1000);
            blockingQueue.put(2);
            Thread.sleep(1000);
            blockingQueue.put(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
