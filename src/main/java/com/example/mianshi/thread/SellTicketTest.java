package com.example.mianshi.thread;

/**
 * @author 朱伟伟
 * @date 2021-03-16 10:49:37
 * @description
 */
public class SellTicketTest {
    public static void main(String[] args) {
        SellTicket sellTicket = new SellTicket();
        Thread thread = new Thread(sellTicket);
        thread.setName("sell ticket 窗口1");

        Thread thread1 = new Thread(sellTicket);
        thread1.setName("sell ticket 窗口2");

        thread.start();
        thread1.start();
    }
}
