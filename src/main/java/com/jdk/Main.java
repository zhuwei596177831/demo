package com.jdk;

/**
 * @author 朱伟伟
 * @date 2021-01-28 13:40:42
 * @description
 */
public class Main {
    public static void main(String[] args) {
//        new Thread(new Worker()).start();
//        new Thread(new Worker()).start();
//        new Thread(new Worker()).start();

//        new Thread(new Worker()).run();
////        new Thread(new Worker()).run();
////        new Thread(new Worker()).run();

//        Worker worker = new Worker();
//        new Thread(worker).run();
//        new Thread(worker).run();
//        new Thread(worker).run();

        Worker worker = new Worker();
        new Thread(worker).start();
        new Thread(worker).start();
        new Thread(worker).start();

    }
}

class Worker implements Runnable {

    private int count = 0;

    @Override
    public void run() {
        while (true) {
            if (++count >= 100) {
                break;
            }
        }
        System.out.println(count);
    }
}
