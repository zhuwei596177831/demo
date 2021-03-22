package com.example.mianshi.thread;

/**
 * @author 朱伟伟
 * @date 2021-03-18 10:17:25
 * @description
 */
public class JoinMain {
    public volatile static int i = 0;

    public static class AThread extends Thread {
        @Override
        public void run() {
            for (i = 0; i < 100000; i++) ;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AThread aThread = new AThread();
        aThread.start();

        aThread.join();

        System.out.println(i);
    }

}
