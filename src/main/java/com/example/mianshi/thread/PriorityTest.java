package com.example.mianshi.thread;

/**
 * @author 朱伟伟
 * @date 2021-03-18 11:10:31
 * @description
 */
public class PriorityTest {
    public static void main(String[] args) {
        Thread high = new Thread(() -> {
            int i = 0;
            while (true) {
                synchronized (PriorityTest.class) {
                    if (i > 10000000) {
                        System.out.println("high completed");
                        break;
                    }
                    i++;
                }
            }
        });
        high.setPriority(Thread.MIN_PRIORITY);
        high.start();

        Thread low = new Thread(() -> {
            int i = 0;
            while (true) {
                synchronized (PriorityTest.class) {
                    if (i > 10000000) {
                        System.out.println("low completed");
                        break;
                    }
                    i++;
                }
            }
        });
        low.setPriority(Thread.MAX_PRIORITY);
        low.start();
    }
}
