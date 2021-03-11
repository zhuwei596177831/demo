package com.example;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 朱伟伟
 * @date 2021-03-11 11:10:18
 * @description
 */
@Configuration
public class ApplicationEventMulticasterRegister {

    /**
     * @param beanFactory:
     * @author: 朱伟伟
     * @date: 2021-03-11 11:20
     * @description: 自定义广播派发器
     * 配置spring事件监听异步任务 Executor
     **/
    @Bean("applicationEventMulticaster")
    ApplicationEventMulticaster applicationEventMulticaster(BeanFactory beanFactory) {
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); //核心线程数 默认 1
        executor.setQueueCapacity(1000); //队列大小 默认Integer.MAX_VALUE
        executor.setMaxPoolSize(20);  //最大线程数 默认Integer.MAX_VALUE
        executor.setKeepAliveSeconds(300); //线程最大空闲时间  默认60
        executor.setThreadNamePrefix("applicationEventMulticaster-async-Executor-"); //指定用于新创建的线程名称的前缀。
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 拒绝策略（一共四种，此处省略）
        executor.setRejectedExecutionHandler(new MyRejectedExecutionHandler()); // 拒绝策略（一共四种，此处省略）
        // 这一步千万不能忘了，否则报错： java.lang.IllegalStateException: ThreadPoolTaskExecutor not initialized
        executor.initialize();
        simpleApplicationEventMulticaster.setTaskExecutor(executor);
        return simpleApplicationEventMulticaster;
    }

    private static class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (!executor.isShutdown()) {
                r.run();
            }
        }
    }

}
