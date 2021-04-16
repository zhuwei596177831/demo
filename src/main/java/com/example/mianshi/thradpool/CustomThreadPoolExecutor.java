package com.example.mianshi.thradpool;

import java.util.concurrent.*;

/**
 * @author 朱伟伟
 * @date 2021-04-16 16:22:53
 * @description
 */
public class CustomThreadPoolExecutor {
    public static void main(String[] args) {
        Runnable runnable = () -> {
//            try {
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        };
        ExecutorService executorService = new ThreadPoolExecutor(10, 50, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("task " + r.toString() + " rejected from " + executor.toString());
            }
        });
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(runnable);
        }
    }
}
