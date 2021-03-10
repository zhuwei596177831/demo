package com.example.mianshi.designpatterns.chainofresponsibility;

/**
 * @author 朱伟伟
 * @date 2021-03-10 16:07:40
 * @description
 */
public class SecondLeaderLeaveHandler extends AbstractLeaveHandler {

    public SecondLeaderLeaveHandler(String leaderName) {
        this.leaderName = leaderName;
    }

    @Override
    protected void handlerRequest(LeaveRequest leaveRequest) {
        System.out.println("二级主管【" + this.leaderName + "】开始审批");
        if (leaveRequest.getDay() <= this.middle) {
            System.out.println("二级主管【" + this.leaderName + "】审批结束");
            return;
        }
        System.out.println("请假天数超出权限范围，交由下一级");
        if (this.getNext() != null) {
            this.getNext().handlerRequest(leaveRequest);
        } else {
            System.out.println("审批拒绝");
        }
    }
}
