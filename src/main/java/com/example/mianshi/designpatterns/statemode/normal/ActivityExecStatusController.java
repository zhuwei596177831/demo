package com.example.mianshi.designpatterns.statemode.normal;

/**
 * @author 朱伟伟
 * @date 2021-03-11 15:10:39
 * @description 活动状态变更控制
 */
public class ActivityExecStatusController {

    public ActivityInfoResult executeStatus(String activityId, Enum<Status> beforeStatus, Enum<Status> afterStatus) {
        if (beforeStatus == null || afterStatus == null || activityId == null) {
            return new ActivityInfoResult("0001", "变更状态失败");
        }
        //编辑 -> 审核 关闭
        if (Status.editing.equals(beforeStatus)) {
            if (Status.pass.equals(afterStatus) || Status.close.equals(afterStatus)) {
                return commonResult(activityId, beforeStatus, afterStatus);
            }
        }
        //待审核 -> 通过 拒绝
        if (Status.checking.equals(beforeStatus)) {
            if (Status.pass.equals(afterStatus) || Status.refuse.equals(afterStatus)) {
                return commonResult(activityId, beforeStatus, afterStatus);
            }
        }

        //通过 ->  关闭
        if (Status.pass.equals(beforeStatus)) {
            if (Status.doing.equals(afterStatus) || Status.close.equals(afterStatus)) {
                return commonResult(activityId, beforeStatus, afterStatus);
            }
        }

        //拒绝 ->  编辑、关闭
        if (Status.refuse.equals(beforeStatus)) {
            if (Status.editing.equals(afterStatus) || Status.close.equals(afterStatus)) {
                return commonResult(activityId, beforeStatus, afterStatus);
            }
        }


        //活动中 ->  开启、关闭
        if (Status.doing.equals(beforeStatus)) {
            if (Status.open.equals(afterStatus) || Status.close.equals(afterStatus)) {
                return commonResult(activityId, beforeStatus, afterStatus);
            }
        }

        //关闭 ->  开启
        if (Status.close.equals(beforeStatus)) {
            if (Status.open.equals(afterStatus)) {
                return commonResult(activityId, beforeStatus, afterStatus);
            }
        }

        //开启 ->  关闭
        if (Status.open.equals(beforeStatus)) {
            if (Status.close.equals(afterStatus)) {
                return commonResult(activityId, beforeStatus, afterStatus);
            }
        }
        return new ActivityInfoResult("0001", "变更状态失败");
    }

    public ActivityInfoResult commonResult(String activityId, Enum<Status> beforeStatus, Enum<Status> afterStatus) {
        if (ActivityService.execStatus(activityId, beforeStatus, afterStatus)) {
            return new ActivityInfoResult("0000", "变更状态成功");
        }
        return new ActivityInfoResult("0001", "变更状态失败");
    }

}
