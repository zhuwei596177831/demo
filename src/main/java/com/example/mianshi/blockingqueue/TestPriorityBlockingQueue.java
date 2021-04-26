package com.example.mianshi.blockingqueue;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 朱伟伟
 * @date 2021-04-26 10:56:18
 * @description 无界队列
 * 没次添加都{@link PriorityBlockingQueue#comparator()}比较
 */
public class TestPriorityBlockingQueue {

    private static final BlockingQueue<PriorityBlockingQueueEntity> blockingQueue = new PriorityBlockingQueue<>();

    public static void main(String[] args) throws Exception {
        blockingQueue.put(new PriorityBlockingQueueEntity(3, UUID.randomUUID().toString()));
        System.out.println("queue：" + blockingQueue);
        blockingQueue.put(new PriorityBlockingQueueEntity(2, UUID.randomUUID().toString()));
        System.out.println("queue：" + blockingQueue);
        blockingQueue.put(new PriorityBlockingQueueEntity(4, UUID.randomUUID().toString()));
        System.out.println("queue：" + blockingQueue);
        blockingQueue.put(new PriorityBlockingQueueEntity(1, UUID.randomUUID().toString()));
        System.out.println("queue：" + blockingQueue);
        System.out.println();
        System.out.println("take：" + blockingQueue.take());
        System.out.println("take：" + blockingQueue.take());
        System.out.println("take：" + blockingQueue.take());
        System.out.println("take：" + blockingQueue.take());
    }
}

class PriorityBlockingQueueEntity implements Comparable<PriorityBlockingQueueEntity> {

    private Integer id;
    private String name;

    public PriorityBlockingQueueEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(PriorityBlockingQueueEntity o) {
        System.out.println("compareTo");
        return this.id.compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "PriorityBlockingQueueEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
