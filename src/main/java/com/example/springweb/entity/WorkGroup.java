package com.example.springweb.entity;

import com.example.generic.BaseEntity;

import java.time.LocalDateTime;

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
     * 进场日期
     */
    private LocalDateTime entryTime;

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
}