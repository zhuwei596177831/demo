package com.example.mianshi.thradpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-03-26 16:37:19
 * @description <p>
 * <p>
 * 当runnable执行时间比period小时：
 * scheduleAtFixedRate周期： period
 * 当runnable执行时间比period大时：
 * scheduleAtFixedRate周期： runnable执行时间
 * <p>
 * scheduleWithFixedDelay周期： runnable执行时间+period
 */
public class ScheduledPool {
    private static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
            try {
//                TimeUnit.SECONDS.sleep(1);
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        //initialDelay+period 后执行 后间隔period执行
        //sleep超过period时 间隔sleep执行
        scheduledExecutorService.scheduleAtFixedRate(runnable, 0, 2, TimeUnit.SECONDS);

        //initialDelay+period 后执行 后间隔sleep+period执行
//        scheduledExecutorService.scheduleWithFixedDelay(runnable, 0, 2, TimeUnit.SECONDS);
    }
}
