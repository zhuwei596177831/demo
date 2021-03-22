package com.example.mianshi.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-03-16 10:47:44
 * @description
 */
public class SellTicket implements Runnable {

    private int num = 100;

    @Override
    public void run() {
        do {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            synchronized (this) {
                this.notify();
                System.out.println(Thread.currentThread().getName() + "sell ticket：" + num--);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (num > 0);
    }
}
