package com.example.mianshi.designpatterns.decorator;

/**
 * @author 朱伟伟
 * @date 2021-03-09 15:34:11
 * @description
 */
public class LoginInterceptor implements HandleInterceptor {
    @Override
    public void send(String msg) {
        System.out.println(msg);
    }
}
