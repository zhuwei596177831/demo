package com.test.getbean.getEarlyBeanReference.async.executor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * @author 朱伟伟
 * @date 2020-12-04 16:11:32
 * @description
 */
public class ExecutorTest {
    public static void main(String[] args) throws Exception {
        Runnable shortTask = () -> System.out.println("complete a short task");
        Runnable shortTask1 = () -> System.out.println("complete a short task");
        Runnable shortTask2 = () -> System.out.println("complete a short task");
        Runnable longTask = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("complete a long task");
        };

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(shortTask);
        executorService.submit(shortTask1);
        executorService.submit(longTask);
        executorService.submit(shortTask2);

        //平滑关闭(未执行完毕的task会继续执行)
        executorService.shutdown();
        //立即关闭 尝试中断正在执行的Task 不一定成功
//        executorService.shutdownNow();
        System.out.println("连接池是否已经关闭：" + executorService.isShutdown());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //task是否已经执行完
        while (!executorService.awaitTermination(1L, TimeUnit.SECONDS)) {
//        while (!executorService.isTerminated()) {
            System.out.println("还有任务在执行，时间：" + formatter.format(LocalDateTime.now()));
        }
        System.out.println("连接池是否已经关闭：" + executorService.isShutdown());
        System.out.println("************");
        System.out.println("callable return " + CallableTest().get());
    }


    public static Future<String> CallableTest() {
        Callable<String> callable = () -> {
            System.out.println("running callable");
            return "哈哈哈";
        };
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> future = executorService.submit(callable);
        executorService.shutdown();
        return future;
    }

}
