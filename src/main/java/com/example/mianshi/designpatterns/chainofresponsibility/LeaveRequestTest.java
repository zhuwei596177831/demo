package com.example.mianshi.designpatterns.chainofresponsibility;

/**
 * @author 朱伟伟
 * @date 2021-03-10 16:12:16
 * @description
 */
public class LeaveRequestTest {
    public static void main(String[] args) {
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setName("朱伟伟");
        leaveRequest.setDay(2);
        AbstractLeaveHandler directLeaderLeaveHandler = new DirectLeaderLeaveHandler("梅亮");
//        directLeaderLeaveHandler.handlerRequest(leaveRequest);

//        leaveRequest.setDay(7);
        AbstractLeaveHandler secondLeaderLeaveHandler = new SecondLeaderLeaveHandler("王飞");
//        directLeaderLeaveHandler.setNext(secondLeaderLeaveHandler);
//        directLeaderLeaveHandler.handlerRequest(leaveRequest);

        leaveRequest.setDay(15);
        AbstractLeaveHandler threeLeaderLeaveHandler = new ThreeLeaderLeaveHandler("黄荣杰");
        directLeaderLeaveHandler.setNext(secondLeaderLeaveHandler).setNext(threeLeaderLeaveHandler);
        directLeaderLeaveHandler.handlerRequest(leaveRequest);

    }
}
