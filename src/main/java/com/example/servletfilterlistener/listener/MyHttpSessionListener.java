package com.example.servletfilterlistener.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author 朱伟伟
 * @date 2020-12-11 16:12:38
 * @description
 */
@WebListener(value = "myHttpSessionListener")
public class MyHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("###########sessionCreated：" + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("###########sessionDestroyed：" + se.getSession().getId());
    }
}
