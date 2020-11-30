package com.example.concurrent.blockingqueue.delayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2020-10-23 17:41:35
 * @description
 */
public class DelayedElemant implements Delayed {
    private final String name;

    public DelayedElemant(String name) {
        this.name = name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    public String getName() {
        return name;
    }
}
