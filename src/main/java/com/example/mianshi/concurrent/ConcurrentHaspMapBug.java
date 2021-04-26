package com.example.mianshi.concurrent;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-04-26 13:57:11
 * @description
 */
public class ConcurrentHaspMapBug {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static final int total = 1000;
    private static final Map<String, String> map = initialMap(total - 100);

    public static void main(String[] args) throws InterruptedException {

        System.out.println("initial map size:" + map.size());
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
//                synchronized (map) {
                int size = map.size();
                System.out.println(Thread.currentThread().getName() + " map Size:" + size);
                if (size < total) {
                    getData(map, total - size);
                }
//                }
            });
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(map.size());

        executorService.shutdown();

    }

    private static void getData(Map<String, String> map, int num) {
        for (int i = 0; i < num; i++) {
            map.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
    }

    private static Map<String, String> initialMap(int initialNum) {
        Map<String, String> map = new ConcurrentHashMap<>(total);
        for (int i = 0; i < initialNum; i++) {
            map.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        return map;
    }
}
