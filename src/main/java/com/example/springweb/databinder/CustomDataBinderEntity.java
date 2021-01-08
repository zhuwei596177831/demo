package com.example.springweb.databinder;

import com.example.generic.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author 朱伟伟
 * @date 2021-01-08 15:57:49
 * @description
 */
public class CustomDataBinderEntity extends BaseEntity {
    private static final long serialVersionUID = 8815286160840515054L;
    private Date startDate;
    private Date endDate;
    private Date dateStr;
    private LocalDate localDate;
    private LocalDate localDateStr;
    private LocalTime localTime;
    private LocalTime localTimeStr;
    private LocalDateTime localDateTime;
    private LocalDateTime localDateTimeStr;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getDateStr() {
        return dateStr;
    }

    public void setDateStr(Date dateStr) {
        this.dateStr = dateStr;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDate getLocalDateStr() {
        return localDateStr;
    }

    public void setLocalDateStr(LocalDate localDateStr) {
        this.localDateStr = localDateStr;
    }

    public LocalTime getLocalTimeStr() {
        return localTimeStr;
    }

    public void setLocalTimeStr(LocalTime localTimeStr) {
        this.localTimeStr = localTimeStr;
    }

    public LocalDateTime getLocalDateTimeStr() {
        return localDateTimeStr;
    }

    public void setLocalDateTimeStr(LocalDateTime localDateTimeStr) {
        this.localDateTimeStr = localDateTimeStr;
    }
}
