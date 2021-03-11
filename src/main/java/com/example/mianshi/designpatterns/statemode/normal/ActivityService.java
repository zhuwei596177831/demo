package com.example.mianshi.designpatterns.statemode.normal;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 朱伟伟
 * @date 2021-03-11 15:02:58
 * @description
 */
public class ActivityService {

    private static Map<String, Enum<Status>> statusMap = new ConcurrentHashMap<>();

    public static void init(String activityId, Enum<Status> status) {
        // 模拟查询活动信息
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.setActivityId(activityId);
        activityInfo.setActivityName("早起学习打卡领奖活动");
        activityInfo.setStatus(status);
        activityInfo.setBeginTime(new Date());
        activityInfo.setEndTime(new Date());
        statusMap.put(activityId, status);
    }

    public static ActivityInfo queryActivityInfo(String activityId) {
        // 模拟查询活动信息
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.setActivityId(activityId);
        activityInfo.setActivityName("早起学习打卡领奖活动");
        activityInfo.setStatus(statusMap.get(activityId));
        activityInfo.setBeginTime(new Date());
        activityInfo.setEndTime(new Date());
        return activityInfo;
    }

    public static Enum<Status> queryActivityStatus(String activityId) {
        //     * 查询活动状态
        return statusMap.get(activityId);
    }

    public static synchronized boolean execStatus(String activityId, Enum<Status> beforeStatus, Enum<Status> afterStatus) {
        ///活动状态变更
        if (beforeStatus == null || !beforeStatus.equals(statusMap.get(activityId))) {
            return false;
        }
        statusMap.put(activityId, afterStatus);
        return true;
    }


}
