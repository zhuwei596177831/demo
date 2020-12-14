package com.example.servletfilterlistener.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 朱伟伟
 * @date 2020-12-11 16:15:01
 * @description
 */
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) sre.getServletRequest();
        System.out.println("###########requestDestroyed:" + httpServletRequest);
//        System.out.println("###########requestDestroyed:" + httpServletRequest.getSession().getId());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) sre.getServletRequest();
        System.out.println("###########requestInitialized:" + httpServletRequest);
//        String id = httpServletRequest.getSession().getId();
//        System.out.println("###########sessionCreated:" + id);
    }
}
