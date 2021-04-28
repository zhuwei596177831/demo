package com.example.mianshi.completablefuture;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author 朱伟伟
 * @date 2021-04-28 13:59:31
 * @description
 */
public class CompletableFutureTest {
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
//        CompletableFuture.runAsync(() -> {
//            System.out.println(new Random().nextInt(100));
//        });
//        CompletableFuture.runAsync(() -> {
//            System.out.println(UUID.randomUUID().toString());
//        }, threadPoolExecutor);
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return UUID.randomUUID().toString();
//        });
//        String s = completableFuture.get();
//        System.out.println(s);

//        默认的线程池：ForkJoinPool.commonPool();  守护线程
//        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(new Random().nextInt(10));
//        });
//        voidCompletableFuture.get();

//        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(new Random().nextInt(10));
//        }, threadPoolExecutor);

        //异常处理的
        CompletableFuture.supplyAsync(() -> new Random().nextInt(10), threadPoolExecutor)
                .thenApply(i -> Integer.toString(i))
                .exceptionally(throwable -> {
                    throwable.printStackTrace();
                    return String.valueOf(new Random().nextInt(5));
                }).thenAccept(System.out::println);
    }
}
