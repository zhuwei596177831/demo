package com.example.mianshi.designpatterns.chainofresponsibility;

/**
 * @author 朱伟伟
 * @date 2021-03-10 16:07:40
 * @description
 */
public class ThreeLeaderLeaveHandler extends AbstractLeaveHandler {

    public ThreeLeaderLeaveHandler(String leaderName) {
        this.leaderName = leaderName;
    }

    @Override
    protected void handlerRequest(LeaveRequest leaveRequest) {
        System.out.println("三级主管【" + this.leaderName + "】开始审批");
        if (leaveRequest.getDay() <= this.max) {
            System.out.println("三级主管【" + this.leaderName + "】审批结束");
            return;
        }
        if (this.getNext() != null) {
            this.getNext().handlerRequest(leaveRequest);
        } else {
            System.out.println("审批拒绝");
        }
    }
}
