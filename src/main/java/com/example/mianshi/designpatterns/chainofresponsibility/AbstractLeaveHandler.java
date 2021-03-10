package com.example.mianshi.designpatterns.chainofresponsibility;

/**
 * @author 朱伟伟
 * @date 2021-03-10 16:05:18
 * @description
 */
public abstract class AbstractLeaveHandler {
    protected final int min = 3;
    protected final int middle = 10;
    protected final int max = 30;
    protected String leaderName;

    protected AbstractLeaveHandler next;

    protected abstract void handlerRequest(LeaveRequest leaveRequest);

    public int getMin() {
        return min;
    }

    public int getMiddle() {
        return middle;
    }

    public int getMax() {
        return max;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public AbstractLeaveHandler getNext() {
        return next;
    }

    public AbstractLeaveHandler setNext(AbstractLeaveHandler next) {
        this.next = next;
        return this.next;
    }
}
