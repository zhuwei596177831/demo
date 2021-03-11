package com.example.mianshi.designpatterns.statemode.refactor;

import com.example.mianshi.designpatterns.statemode.normal.ActivityInfoResult;
import com.example.mianshi.designpatterns.statemode.normal.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2021-03-11 16:00:31
 * @description
 */
public class StateHandler {
    private final Map<Enum<Status>, State> map = new HashMap();

    public StateHandler() {
        map.put(Status.editing, new EditState());
        map.put(Status.checking, new CheckState());
        map.put(Status.pass, new PassState());
        map.put(Status.refuse, new RefuseState());
        map.put(Status.doing, new DoingState());
        map.put(Status.open, new OpenState());
        map.put(Status.close, new CloseState());
    }

    public ActivityInfoResult check(String activityId, Enum<Status> status) {
        return map.get(status).check(activityId, status);
    }

    public State getState(Enum<Status> status) {
        return map.get(status);
    }

}
