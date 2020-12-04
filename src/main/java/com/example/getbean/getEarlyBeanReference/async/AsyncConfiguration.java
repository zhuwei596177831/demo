package com.example.getbean.getEarlyBeanReference.async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 朱伟伟
 * @date 2020-12-04 10:01:32
 * @description
 * @see org.springframework.scheduling.annotation.Async
 * 1、注解一般用在方法上，如果用在类上，那么这个类所有的方法都是异步执行的；
 * 2、可以放在任何方法上，哪怕你是private的（若是同类调用，请务必注意注解失效的情况~~~）
 * 3、所使用的@Async注解方法的类对象应该是Spring容器管理的bean对象
 * 4、可以放在接口处（或者接口方法上）。但是只有使用的是JDK的动态代理时才有效，CGLIB会失效。因此建议：统一写在实现类的方法上
 * 5、需要注解@EnableAsync来开启异步注解的支持
 * 6、若你希望得到异步调用的返回值，请你的返回值用Futrue变量包装起来
 */
@Configuration(proxyBeanMethods = false)
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

    /**
     * @author: 朱伟伟
     * @date: 2020-12-04 17:37
     * @description: 自定义async 线程池
     * SimpleAsyncTaskExecutor：异步执行用户任务的SimpleAsyncTaskExecutor。每次执行客户提交给它的任务时，它会启动新的线程，
     * 并允许开发者控制并发线程的上限（concurrencyLimit），从而起到一定的资源节流作用。
     * 默认时，concurrencyLimit取值为-1，即**不启用**资源节流
     * 所以它不是真的线程池，这个类不重用线程，每次调用都会创建一个新的线程（因此建议我们在使用@Aysnc的时候，自己配置一个线程池，节约资源）
     **/
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); //核心线程数
        executor.setMaxPoolSize(20);  //最大线程数
        executor.setQueueCapacity(1000); //队列大小
        executor.setKeepAliveSeconds(300); //线程最大空闲时间
        executor.setThreadNamePrefix("async-Executor-"); //指定用于新创建的线程名称的前缀。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略（一共四种，此处省略）
        // 这一步千万不能忘了，否则报错： java.lang.IllegalStateException: ThreadPoolTaskExecutor not initialized
        executor.initialize();
        return executor;
//        return new SimpleAsyncTaskExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
