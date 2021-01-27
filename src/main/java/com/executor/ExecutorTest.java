package com.executor;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author 朱伟伟
 * @date 2021-01-27 14:57:35
 * @description 当线程数小于核心线程数时，创建线程。
 * 当线程数大于等于核心线程数，且任务队列未满时，将任务放入任务队列。
 * 当线程数大于等于核心线程数，且任务队列已满
 * 若线程数小于最大线程数，创建线程
 * 若线程数等于最大线程数，抛出异常，拒绝任务
 */
public class ExecutorTest {


    public static void main(String[] args) {
//        testCachePool();
//        testFixedThreadPool();
//        testSingleThreadPool();
//        testScheduledThreadPool();
//        testOOM();
//        Arrays.asList(1, 2, 3, 4, 5, 6).forEach(i -> {
//            if (i < 4) {
////                continue;
////                break;
//                return;
//            }
//            System.out.println(i);
//        });

        Random random = new Random(100);
        for (int i = 0; i < 4; i++) {
            System.out.println(random.nextInt(5));
        }
        System.out.println("==========");
        Random random1 = new Random(100);
        for (int i = 0; i < 4; i++) {
            System.out.println(random1.nextInt(5));
        }

    }

    private static void testCachePool() {
        //核心线程数:0
        // 最大线程数:Integer.MAX_VALUE
        //线程空闲时间:60s  当线程空闲时间达到keepAliveTime时，线程会被销毁，直到线程数量=corePoolSize 如果allowCoreThreadTimeout=true，则会直到线程数量=0（这个特性需要注意）
        //new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
        //可以实现线程的复用
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            executorService.execute(() -> {
                System.out.println("任务：" + index + Thread.currentThread().getName());
                try {
                    Thread.sleep(index * 100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    private static void testFixedThreadPool() {
        //LinkedBlockingQueue 无界队列
        //ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(() -> {
                try {
                    Thread.sleep(index * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("任务：" + index + Thread.currentThread().getName());
            });
        }
        executorService.shutdown();
    }

    private static void testSingleThreadPool() {
        //LinkedBlockingQueue 无界队列
        //new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>())
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            executorService.execute(() -> {
                System.out.println("任务：" + index + Thread.currentThread().getName());
                try {
                    Thread.sleep(index * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    private static void testScheduledThreadPool() {
        //super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,new DelayedWorkQueue())
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 5; i++) {
            final int index = i;
            executorService.schedule(() -> {
                System.out.println("任务：" + index + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, 2L, TimeUnit.SECONDS);
        }
    }

    private static void testOOM() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        executorService.shutdownNow();
    }


}
