package com.example.springweb.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.generic.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class WorkGroup extends BaseEntity {
    private static final long serialVersionUID = -1070687930887054576L;
    /**
     * 班组ID
     */
    private String id;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 班组名称
     */
    @NotEmpty(message = "班组名称不能为空")
    private String name;

    /**
     * 班组长姓名
     */
    private String leaderName;

    /**
     * 班组长身份证号码
     */
    private String leaderIdNo;

    /**
     * 班组长手机号
     */
    private String leaderPhone;

    /**
     * 班组长性别 0:男 1:女
     */
    private Integer leaderGender;

    /**
     * 出场日期
     */
    private LocalDateTime exitTime;

    /**
     * 所属劳务单位
     */
    private String organName;

    /**
     * 备注
     */
    private String remark;
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date dateTimeFormatDate;
    @DateTimeFormat(pattern = "yyyyMMdd HH:mm:ss")
    private LocalDateTime dateTimeFormatLocalDateTime;
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate dateTimeFormatLocalDate;
    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime dateTimeFormatLocalTime;
    /**
     * 进场日期
     */
//    @JSONField(format = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime entryTime;
//    @JSONField(format = "yyyy/MM/dd HH:mm:ss")
    private Date date;
//    @JSONField(format = "yyyy/MM/dd")
    private LocalDate localDate;
//    @JSONField(format = "HH:mm:ss")
    private LocalTime localTime;

    public LocalDate getDateTimeFormatLocalDate() {
        return dateTimeFormatLocalDate;
    }

    public void setDateTimeFormatLocalDate(LocalDate dateTimeFormatLocalDate) {
        this.dateTimeFormatLocalDate = dateTimeFormatLocalDate;
    }

    public LocalTime getDateTimeFormatLocalTime() {
        return dateTimeFormatLocalTime;
    }

    public void setDateTimeFormatLocalTime(LocalTime dateTimeFormatLocalTime) {
        this.dateTimeFormatLocalTime = dateTimeFormatLocalTime;
    }

    public LocalDateTime getDateTimeFormatLocalDateTime() {
        return dateTimeFormatLocalDateTime;
    }

    public void setDateTimeFormatLocalDateTime(LocalDateTime dateTimeFormatLocalDateTime) {
        this.dateTimeFormatLocalDateTime = dateTimeFormatLocalDateTime;
    }

    public Date getDateTimeFormatDate() {
        return dateTimeFormatDate;
    }

    public void setDateTimeFormatDate(Date dateTimeFormatDate) {
        this.dateTimeFormatDate = dateTimeFormatDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderIdNo() {
        return leaderIdNo;
    }

    public void setLeaderIdNo(String leaderIdNo) {
        this.leaderIdNo = leaderIdNo;
    }

    public String getLeaderPhone() {
        return leaderPhone;
    }

    public void setLeaderPhone(String leaderPhone) {
        this.leaderPhone = leaderPhone;
    }

    public Integer getLeaderGender() {
        return leaderGender;
    }

    public void setLeaderGender(Integer leaderGender) {
        this.leaderGender = leaderGender;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
