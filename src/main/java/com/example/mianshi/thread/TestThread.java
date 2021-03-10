package com.example.mianshi.thread;

/**
 * @author 朱伟伟
 * @date 2021-03-06 13:43:38
 * @description
 */
public class TestThread {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.setName("myThread");
        myThread.start();

        MyThread myThread1 = new MyThread();
        myThread1.setName("myThread1");
        myThread1.start();
        try {
            System.out.println("线程" + Thread.currentThread().getName() + "等待");
            myThread.join();
            myThread1.join();
            System.out.println("线程" + Thread.currentThread().getName() + "继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("进入线程" + Thread.currentThread().getName());
            try {
                Thread.currentThread().sleep(2000); //模拟执行任务需要2秒
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("线程" + Thread.currentThread().getName() + "执行完毕");
        }
    }
}
