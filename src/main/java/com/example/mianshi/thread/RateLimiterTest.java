package com.example.mianshi.thread;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-03-26 14:55:31
 * 令牌桶算法 限流之：
 * @description {@link com.google.common.util.concurrent.RateLimiter}
 */
public class RateLimiterTest {

    /**
     * 每秒只能接受两个请求
     */
    private static RateLimiter rateLimiter = RateLimiter.create(2);

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        };

        for (int i = 0; i < 50; i++) {
            //限流（获取请求 阻塞 直到获取到）
//            rateLimiter.acquire();

            try {
                TimeUnit.MILLISECONDS.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //尝试获取 成功 true 失败 false  不阻塞
            if (!rateLimiter.tryAcquire()) {
                System.out.println("丢弃");
                continue;
            }

            new Thread(runnable).start();
        }
    }
}
