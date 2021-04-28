package com.example.mianshi.thread.readwritelock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author 朱伟伟
 * @date 2021-04-28 14:59:49
 * @description 读写锁的升级：
 * {@link java.util.concurrent.locks.StampedLock}
 * problem：写时 存在大量读时 阻塞写 造成写"饥饿"
 * {@link java.util.concurrent.locks.ReentrantReadWriteLock}
 */
public class StampedLockTest {
    private static StampedLock stampedLock = new StampedLock();

    private double x;
    private double y;


    public void move(double x, double y) {
        long stamp = stampedLock.writeLock();
        try {
            this.x += x;
            this.y += y;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public double read() {
        long flag = stampedLock.tryOptimisticRead();
        double currentX = x;
        double currentY = y;
        //读取过程中 数据被其他线程修改
        if (!stampedLock.validate(flag)) {
            long stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.max(currentX, currentY);
    }

}
