package com.example.mianshi.designpatterns.statemode.normal;

/**
 * @author 朱伟伟
 * @date 2021-03-11 14:59:53
 * @description
 */
public enum Status {
    /**
     * 编辑
     */
    editing,
    /**
     * 待审核
     */
    checking,
    /**
     * 审核通过（任务扫描成活动中）
     */
    pass,
    /**
     * 审核拒绝（可以撤审到编辑状态）
     */
    refuse,
    /**
     * 活动中
     */
    doing,
    /**
     * 活动关闭
     */
    close,
    /**
     * 活动开启（任务扫描成活动中）
     */
    open;
}
