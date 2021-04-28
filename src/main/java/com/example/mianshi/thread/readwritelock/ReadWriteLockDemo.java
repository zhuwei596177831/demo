package com.example.mianshi.thread.readwritelock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 朱伟伟
 * @date 2021-03-23 10:21:12
 * @description
 */
//@Component
public class ReadWriteLockDemo implements ApplicationRunner {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String TOKEN_NAME = "token";
    private static final String EXPIRE_DATE_NAME = "expireDate";

    private final Logger logger = LoggerFactory.getLogger(ReadWriteLockDemo.class);

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static Map<String, Object> map = new HashMap<>();

    private final AtomicInteger atomicInteger = new AtomicInteger(1);

//    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5, r -> {
//        Thread thread = new Thread(r);
//        thread.setName("token-refresh-" + atomicInteger.getAndIncrement());
//        thread.setDaemon(true);
//        return thread;
//    });

    private final ScheduledExecutorService customScheduledService = new ScheduledThreadPoolExecutor(1, r -> {
        Thread thread = new Thread(r);
        thread.setName("token-refresh-" + atomicInteger.getAndIncrement());
        thread.setDaemon(true);
        return thread;
    }, new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (!executor.isShutdown()) {
                r.run();
            }
        }
    }) {
        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            logger.info(t.getName() + "start running at " + LocalDateTime.now().format(dateTimeFormatter));
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            logger.info("finish running at " + LocalDateTime.now().format(dateTimeFormatter));
        }

        @Override
        protected void terminated() {
            logger.info("threadPool terminated");
        }
    };

    private void refreshToken() {
        if (map.get(TOKEN_NAME) == null || LocalDateTime.now().isAfter((LocalDateTime) map.get(EXPIRE_DATE_NAME))) {
            logger.info(Thread.currentThread().getName() + " begin refresh token......");
            readWriteLock.writeLock().lock();
            try {
                map.put(TOKEN_NAME, UUID.randomUUID().toString());
                map.put(EXPIRE_DATE_NAME, LocalDateTime.now().plusMinutes(10));
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
    }

    public String getToken() {
        readWriteLock.readLock().lock();
        try {
            return (String) map.get(TOKEN_NAME);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        for (int i = 0; i < new Random().nextInt(5); i++) {
//            scheduledExecutorService.scheduleAtFixedRate(this::refreshToken, 5, 300, TimeUnit.SECONDS);
//        }
        customScheduledService.scheduleAtFixedRate(this::refreshToken, 3, 10, TimeUnit.SECONDS);
    }

}
