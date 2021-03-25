package com.example.mianshi.thread.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-03-25 17:25:01
 * @description 循环栅栏
 * 类似{@link java.util.concurrent.CountDownLatch} 实现线程的等待
 * 区别：可以循环使用
 * {@link CyclicBarrier#await()}
 * 调用await方法的线程告诉CyclicBarrier自己已经到达同步点，然后当前线程被阻塞。直到parties个参与线程调用了await方法
 */
public class CyclicBarrierDemo {


    public static void main(String[] args) {
        //5个人到齐后才开始开会
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,
                //5个人都到期后执行的代码
                () -> {
//                    System.out.println(Thread.currentThread().getName() + "终于到达会议室");
                    System.out.println("5个人都已到齐");
                });
        //模拟5个人
        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + "到达会议室");
                    //到达后开始等待 知道最后一个人到达
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            thread.setName("人员" + i);
            thread.start();
        }
    }

}
