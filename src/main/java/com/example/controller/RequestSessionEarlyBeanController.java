package com.example.controller;

import com.example.getbean.getEarlyBeanReference.SessionEarlyBean;
import com.example.getbean.getEarlyBeanReference.RequestEarlyBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱伟伟
 * @date 2020-12-02 17:47:47
 * @description
 */
@RestController
public class RequestSessionEarlyBeanController {
    @Autowired
    ObjectProvider<RequestEarlyBean> requestEarlyBeanObjectProvider;
    @Autowired
    ObjectFactory<SessionEarlyBean> sessionEarlyBeanObjectFactory;

    @GetMapping("/requestEarlyBean")
    public String test() {
        System.out.println(requestEarlyBeanObjectProvider.getObject());
        System.out.println(sessionEarlyBeanObjectFactory.getObject());
        return "";
    }
}
