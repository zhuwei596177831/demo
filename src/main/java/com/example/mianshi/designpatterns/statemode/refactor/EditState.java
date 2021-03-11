package com.example.mianshi.designpatterns.statemode.refactor;

import com.example.mianshi.designpatterns.statemode.normal.ActivityInfoResult;
import com.example.mianshi.designpatterns.statemode.normal.ActivityService;
import com.example.mianshi.designpatterns.statemode.normal.Status;

/**
 * @author 朱伟伟
 * @date 2021-03-11 15:33:39
 * @description
 */
public class EditState extends State {

    @Override
    public ActivityInfoResult check(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, Status.checking);
        return new ActivityInfoResult("0000", "活动提审成功");
    }

    @Override
    public ActivityInfoResult pass(String activityId, Enum<Status> currentStatus) {
        return new ActivityInfoResult("0001", "编辑中不可审核通过");
    }

    @Override
    public ActivityInfoResult refuse(String activityId, Enum<Status> currentStatus) {
        return new ActivityInfoResult("0001", "编辑中不可审核拒绝");
    }

    @Override
    public ActivityInfoResult doing(String activityId, Enum<Status> currentStatus) {
        return new ActivityInfoResult("0001", "编辑中活动不可执行活动中变更");
    }

    @Override
    public ActivityInfoResult close(String activityId, Enum<Status> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, Status.close);
        return new ActivityInfoResult("0000", "活动关闭成功");
    }

    @Override
    public ActivityInfoResult open(String activityId, Enum<Status> currentStatus) {
        return new ActivityInfoResult("0001", "非关闭活动不可开启");
    }


}
