package com.example.mianshi.designpatterns.decorator;

/**
 * @author 朱伟伟
 * @date 2021-03-09 15:34:53
 * @description
 */
public abstract class HandleInterceptorAdapter implements HandleInterceptor {


    protected final HandleInterceptor handleInterceptor;

    public HandleInterceptorAdapter(HandleInterceptor handleInterceptor) {
        this.handleInterceptor = handleInterceptor;
    }

    @Override
    public void send(String msg) {
        handleInterceptor.send(msg);
    }
}
