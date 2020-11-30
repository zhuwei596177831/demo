package com.example.applicationEvent;

import org.springframework.context.ApplicationEvent;

/**
 * @author 朱伟伟
 * @date 2020-10-23 15:42:47
 * @description
 */
public class MyApplicationEvent extends ApplicationEvent {
    private static final long serialVersionUID = -5328544493952542469L;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MyApplicationEvent(Object source) {
        super(source);
    }
}
