package com.example.servletfilterlistener.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author 朱伟伟
 * @date 2020-12-11 16:16:11
 * @description
 */
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("###########contextInitialized:" + sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("###########contextDestroyed:" + sce.getServletContext());
    }
}
