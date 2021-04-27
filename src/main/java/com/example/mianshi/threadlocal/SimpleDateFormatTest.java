package com.example.mianshi.threadlocal;

import com.google.common.collect.Lists;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-04-27 09:31:37
 * @description SimpleDateFormat线程安全问题
 */
public class SimpleDateFormatTest {
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    //错误方式：
    static {
        DATE_FORMAT_THREAD_LOCAL.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static void main(String[] args) {
        List<String> dates = Lists.newArrayList(
                "2020-11-25 15:25:33",
                "2020-11-25 15:25:33",
                "2020-11-25 15:25:33",
                "2020-11-25 15:25:33",
                "2020-11-25 15:25:33");
        dates.forEach(d -> {
            executorService.execute(() -> {
                try {
//                    System.out.println(Thread.currentThread().getName() + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d));
                    System.out.println(Thread.currentThread().getName() + DATE_FORMAT_THREAD_LOCAL.get().parse(d));
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
        executorService.shutdown();
    }
}
