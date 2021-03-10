package com.example.mianshi.designpatterns.decorator;

import java.util.List;

/**
 * @author 朱伟伟
 * @date 2021-03-09 15:41:05
 * @description
 */
public class ListHandleInterceptor extends HandleInterceptorAdapter {
    public ListHandleInterceptor(HandleInterceptor handleInterceptor) {
        super(handleInterceptor);
    }

    public void sendMsgs(List<String> msgs) {
        msgs.forEach(this.handleInterceptor::send);
    }

}
