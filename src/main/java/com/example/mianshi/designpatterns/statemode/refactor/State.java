package com.example.mianshi.designpatterns.statemode.refactor;

import com.example.mianshi.designpatterns.statemode.normal.ActivityInfoResult;
import com.example.mianshi.designpatterns.statemode.normal.Status;

/**
 * @author 朱伟伟
 * @date 2021-03-11 15:31:04
 * @description
 */
public abstract class State {
    /**
     * 活动提审
     *
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return 执行结果
     */
    public abstract ActivityInfoResult check(String activityId, Enum<Status> currentStatus);

    /**
     * 审核通过
     *
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return 执行结果
     */
    public abstract ActivityInfoResult pass(String activityId, Enum<Status> currentStatus);

    /**
     * 审核拒绝
     *
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return 执行结果
     */
    public abstract ActivityInfoResult refuse(String activityId, Enum<Status> currentStatus);

    /**
     * 活动执行
     *
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return 执行结果
     */
    public abstract ActivityInfoResult doing(String activityId, Enum<Status> currentStatus);

    /**
     * 活动关闭
     *
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return 执行结果
     */
    public abstract ActivityInfoResult close(String activityId, Enum<Status> currentStatus);

    /**
     * 活动开启
     *
     * @param activityId    活动ID
     * @param currentStatus 当前状态
     * @return 执行结果
     */
    public abstract ActivityInfoResult open(String activityId, Enum<Status> currentStatus);

}
