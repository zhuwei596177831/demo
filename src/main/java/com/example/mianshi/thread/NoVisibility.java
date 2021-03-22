package com.example.mianshi.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-03-18 10:46:22
 * @description
 */
public class NoVisibility {
    private static volatile boolean ready = false;
    private static int number = 0;

    public static class ReaderThread extends Thread {

        @Override
        public void run() {
            while (ready) {
                System.out.println(number);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(5000);
    }

}
