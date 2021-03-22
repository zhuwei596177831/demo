package com.example.mianshi.thread;

import java.io.IOException;

/**
 * @author 朱伟伟
 * @date 2021-03-18 11:03:09
 * @description
 */
public class DaemonTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("alive");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //设为守护线程 main主线程退出 随之退出
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(2000);
    }
}
