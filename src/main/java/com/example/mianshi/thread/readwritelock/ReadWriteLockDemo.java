package com.example.mianshi.thread.readwritelock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 朱伟伟
 * @date 2021-03-23 10:21:12
 * @description
 */
@Component
public class ReadWriteLockDemo implements ApplicationRunner {

    private static final String TOKEN_NAME = "token";
    private static final String EXPIRE_DATE_NAME = "expireDate";

    private final Logger logger = LoggerFactory.getLogger(ReadWriteLockDemo.class);

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static Map<String, Object> map = new HashMap<>();

    private void refreshToken() {
        if (map.get(TOKEN_NAME) == null || LocalDateTime.now().isAfter((LocalDateTime) map.get(EXPIRE_DATE_NAME))) {
            logger.info("begin refresh token......");
            readWriteLock.writeLock().lock();
            try {
                map.put(TOKEN_NAME, UUID.randomUUID().toString());
                map.put(EXPIRE_DATE_NAME, LocalDateTime.now().plusMinutes(10));
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
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(this::refreshToken, 5, 300, TimeUnit.SECONDS);
    }

}
