package com.example.aop;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 朱伟伟
 * @date 2020-09-15 17:48:38
 * @description
 */
public class PersonComponent {
    @Autowired
    private UserComponent userComponent;

    public void getPersonId() {
        System.out.println("getPersonId......");
    }

}
