package com.example.mianshi.thread.readwritelock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 朱伟伟
 * @date 2021-03-23 09:48:16
 * @description 读写锁
 * 读读——不阻塞
 * 读写——读时阻塞写 写时阻塞读
 * 写写——阻塞
 */
public class ReadWriteLockTest {
    private static ReentrantLock reentrantLock = new ReentrantLock();

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    private static int value;

    private static int read(Lock lock) {
        try {
            readWriteLock.readLock().lock();
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return value;
    }

    private static void write(Lock lock) {
        try {
            readWriteLock.writeLock().lock();
            ReadWriteLockTest.value = new Random().nextInt();
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {

        Runnable read = () -> {
            ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
            readWriteLockTest.read(readLock);
            readWriteLockTest.read(reentrantLock);
        };

        for (int i = 0; i < 20; i++) {
            new Thread(read).start();
        }

        Runnable write = () -> {
            ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
            readWriteLockTest.write(writeLock);
            readWriteLockTest.write(reentrantLock);
        };

        for (int i = 0; i < 2; i++) {
            new Thread(write).start();
        }

    }

}
