package com.example.mianshi.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-03-18 10:33:16
 * @description
 */
public class volatileTest {

    public static int i = 0;

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int i1 = 0; i1 < 1000; i1++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i1 = 0; i1 < 10; i1++) {
            threads[i1] = new Thread(new AddThread());
            threads[i1].start();
        }
        for (int i1 = 0; i1 < 10; i1++) {
            threads[i1].join();
        }
        System.out.println(i);
    }


}
