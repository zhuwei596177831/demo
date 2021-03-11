package com.example.mianshi.designpatterns.statemode.refactor;

import com.example.mianshi.designpatterns.statemode.normal.ActivityInfoResult;
import com.example.mianshi.designpatterns.statemode.normal.ActivityService;
import com.example.mianshi.designpatterns.statemode.normal.Status;

/**
 * @author 朱伟伟
 * @date 2021-03-11 15:41:52
 * @description
 */
public class CheckState extends State {
    @Override
    public ActivityInfoResult check(String activityId, Enum<Status> currentStatus) {
        return new ActivityInfoResult("0000", "待审核中无法提审");
    }

    @Override
    public ActivityInfoResult pass(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, Status.pass);
        return new ActivityInfoResult("0000", "审核通过");
    }

    @Override
    public ActivityInfoResult refuse(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, Status.refuse);
        return new ActivityInfoResult("0000", "审核拒绝");
    }

    @Override
    public ActivityInfoResult doing(String activityId, Enum<Status> currentStatus) {
        return new ActivityInfoResult("0000", "待审核中无法开启活动");
    }

    @Override
    public ActivityInfoResult close(String activityId, Enum<Status> currentStatus) {
        return new ActivityInfoResult("0000", "待审核中无法关闭");
    }

    @Override
    public ActivityInfoResult open(String activityId, Enum<Status> currentStatus) {
        return new ActivityInfoResult("0000", "待审核中无法开启");
    }
}
