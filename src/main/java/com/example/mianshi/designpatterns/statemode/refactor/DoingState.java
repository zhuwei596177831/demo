package com.example.mianshi.designpatterns.statemode.refactor;

import com.example.mianshi.designpatterns.statemode.normal.ActivityInfoResult;
import com.example.mianshi.designpatterns.statemode.normal.ActivityService;
import com.example.mianshi.designpatterns.statemode.normal.Status;

/**
 * @author 朱伟伟
 * @date 2021-03-11 15:49:33
 * @description
 */
public class DoingState extends State {
    @Override
    public ActivityInfoResult check(String activityId, Enum<Status> currentStatus) {
        return new ActivityInfoResult("0001", "活动中不可编辑");
    }

    @Override
    public ActivityInfoResult pass(String activityId, Enum<Status> currentStatus) {
        return new ActivityInfoResult("0001", "活动中不可审核通过");
    }

    @Override
    public ActivityInfoResult refuse(String activityId, Enum<Status> currentStatus) {
        return new ActivityInfoResult("0001", "活动中不可拒绝");
    }

    @Override
    public ActivityInfoResult doing(String activityId, Enum<Status> currentStatus) {
        return new ActivityInfoResult("0001", "活动中不可活动");
    }

    @Override
    public ActivityInfoResult close(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, Status.close);
        return new ActivityInfoResult("0001", "活动关闭成功");
    }

    @Override
    public ActivityInfoResult open(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, Status.open);
        return new ActivityInfoResult("0001", "活动开启成功");
    }
}
