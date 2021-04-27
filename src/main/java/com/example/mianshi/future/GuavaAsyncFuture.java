package com.example.mianshi.future;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author 朱伟伟
 * @date 2021-04-27 16:12:02
 * @description
 */
public class GuavaAsyncFuture {

    private static ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());

    public static void main(String[] args) throws InterruptedException {
        Callable<String> callable = () -> {
            StringBuilder builder = new StringBuilder(255);
            for (int i = 0; i < 10; i++) {
                builder.append(UUID.randomUUID().toString());
                TimeUnit.SECONDS.sleep(1);
            }
            return builder.toString();
        };
//        FutureTask<String> futureTask = new FutureTask<>(callable);
        ListenableFuture<String> listenableFuture = listeningExecutorService.submit(callable);
        //添加异步完成通知
//        listenableFuture.addListener(() -> {
//            System.out.println("async call success");
//            try {
//                System.out.println(listenableFuture.get());
//            } catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }, MoreExecutors.directExecutor());
        //添加带异常处理异步通知
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println("async call success");
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("async call fail");
                t.printStackTrace();
            }
        }, MoreExecutors.directExecutor());
        System.out.println("===============");
        TimeUnit.SECONDS.sleep(3);
        System.out.println(listenableFuture.isDone());
        System.out.println(listenableFuture.isCancelled());
        System.out.println("main done");
        listeningExecutorService.shutdown();
    }
}
