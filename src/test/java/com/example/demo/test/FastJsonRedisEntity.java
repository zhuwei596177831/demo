package com.example.demo.test;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.generic.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author 朱伟伟
 * @date 2021-04-29 11:16:56
 * @description
 */
public class FastJsonRedisEntity extends BaseEntity {
    private static final long serialVersionUID = 4266161984101152995L;
    private String name;
    private Integer age;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;
    @JSONField(format = "yyyy-MM-dd")
    private LocalDate localDate;
    @JSONField(format = "HH:mm:ss")
    private LocalTime localTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    public FastJsonRedisEntity(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.localDateTime = LocalDateTime.now();
        this.localDate = LocalDate.now();
        this.localTime = LocalTime.now();
        this.date = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
