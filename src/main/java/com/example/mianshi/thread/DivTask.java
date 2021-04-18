package com.example.mianshi.thread;

import java.util.concurrent.*;

/**
 * @author 朱伟伟
 * @date 2021-04-18 21:46:09
 * @description 堆栈去哪了 在线程池中寻找堆栈
 */
public class DivTask implements Runnable {
    int a;
    int b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                10, 10, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy()
        );
        TraceThreadPoolExecutor traceThreadPoolExecutor = new TraceThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
        for (int i = 0; i < 5; i++) {
            //100/0 异常被吃掉了
//            threadPoolExecutor.submit(new DivTask(100, i));

            //改进方案一、（只能得到部分异常信息 具体的任务在哪里提交的无法看到）
            // 1、
//            threadPoolExecutor.execute(new DivTask(100, i));
            //2、
//            Future<?> future = threadPoolExecutor.submit(new DivTask(100, i));
//            future.get();

            traceThreadPoolExecutor.execute(new DivTask(100, i));
        }
        threadPoolExecutor.shutdown();
        traceThreadPoolExecutor.shutdown();
    }

    @Override
    public void run() {
        System.out.println(a / b);
    }
}

/**
 * @author: 朱伟伟
 * @date: 2021-04-18 22:04
 * @description: 方案二：自定义扩展线程池 完美解决
 **/
class TraceThreadPoolExecutor extends ThreadPoolExecutor {


    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(wrapRunnable(command, clientTrace(), Thread.currentThread().getName()));
    }

    @Override
    public Future<?> submit(Runnable task) {
        return super.submit(wrapRunnable(task, clientTrace(), Thread.currentThread().getName()));
    }

    private Exception clientTrace() {
        return new Exception("client stack trace");
    }

    private Runnable wrapRunnable(final Runnable task, final Exception clientStack, String threadName) {
        return () -> {
            try {
                task.run();
            } catch (Exception e) {
                clientStack.printStackTrace();
                throw e;
            }
        };
    }

}
