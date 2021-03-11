package com.example.mianshi.designpatterns.statemode.normal;

/**
 * @author 朱伟伟
 * @date 2021-03-11 15:21:19
 * @description
 */
public class ActivityInfoTest {
    public static void main(String[] args) {
        ActivityService.init("111", Status.doing);

        ActivityExecStatusController activityExecStatusController = new ActivityExecStatusController();
        ActivityInfoResult activityInfoResult = activityExecStatusController.executeStatus("111", Status.doing, Status.editing);
        System.out.println(activityInfoResult);
    }
}
