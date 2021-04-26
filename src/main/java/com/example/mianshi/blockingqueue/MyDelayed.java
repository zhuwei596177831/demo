package com.example.mianshi.blockingqueue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author 朱伟伟
 * @date 2021-04-26 09:45:27
 * @description
 */
public class MyDelayed implements Delayed {

    private String name;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireDate;
    private long delaySeconds;

    public MyDelayed(String name, long delaySeconds) {
        this.name = name;
        this.delaySeconds = delaySeconds;
        this.expireDate = LocalDateTime.now().plusSeconds(delaySeconds);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(Duration.between(LocalDateTime.now(), expireDate).getSeconds(), TimeUnit.SECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return this.expireDate.isAfter(((MyDelayed) o).getExpireDate()) ? 1 : -1;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public long getDelaySeconds() {
        return delaySeconds;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
