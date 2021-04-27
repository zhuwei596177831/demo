package com.example.mianshi.future;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author 朱伟伟
 * @date 2021-04-27 16:12:02
 * @description
 */
public class FutureMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<String> callable = () -> {
            StringBuilder builder = new StringBuilder(255);
            for (int i = 0; i < 10; i++) {
                builder.append(UUID.randomUUID().toString());
                TimeUnit.SECONDS.sleep(1);
            }
            return builder.toString();
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        FutureTask<String> futureTask = new FutureTask<>(callable);
//        executorService.submit(futureTask);

//        Future<String> future = executorService.submit(callable);

//        System.out.println("===============");
//        TimeUnit.SECONDS.sleep(3);
//        System.out.println(futureTask.isDone());
//        System.out.println(futureTask.isDone());
//        System.out.println(futureTask.isCancelled());
//        while (!futureTask.isDone()) {
//            System.out.println("获取结果：" + futureTask.get());
//        }
//        System.out.println(futureTask.isCancelled());

        System.out.println("===============");
//        TimeUnit.SECONDS.sleep(3);
//        System.out.println(future.isDone());
//        System.out.println(future.isDone());
//        System.out.println(future.isCancelled());
//        while (!future.isDone()) {
//            System.out.println("获取结果：" + future.get());
//        }
//        System.out.println(future.isCancelled());


        executorService.shutdown();
    }
}
