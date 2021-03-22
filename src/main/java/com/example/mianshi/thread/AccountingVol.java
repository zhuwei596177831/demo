package com.example.mianshi.thread;

/**
 * @author 朱伟伟
 * @date 2021-03-18 11:21:09
 * @description
 */
public class AccountingVol {

    //    static volatile int i = 0;
    static int i = 0;

    static Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i1 = 0; i1 < 10000000; i1++) {
                synchronized (monitor) {
                    i++;
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            for (int i1 = 0; i1 < 10000000; i1++) {
                synchronized (monitor) {
                    i++;
                }
            }
        });
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(i);
    }
}
