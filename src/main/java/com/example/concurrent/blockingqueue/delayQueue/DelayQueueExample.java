package com.example.concurrent.blockingqueue.delayQueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * @author 朱伟伟
 * @date 2020-10-23 17:40:59
 * @description
 */
public class DelayQueueExample {
    public static void main(String[] args) {
        DelayQueue<DelayedElemant> delayQueue = new DelayQueue<>();
        DelayedElemant delayed = new DelayedElemant("aaa");
        DelayedElemant delayedElemant = new DelayedElemant("bbb");
        delayQueue.put(delayed);
        delayQueue.put(delayedElemant);
        try {
            delayQueue.take();
            delayQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
