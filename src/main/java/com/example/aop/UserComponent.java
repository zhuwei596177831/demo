package com.example.aop;


import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 朱伟伟
 * @date 2020-09-14 10:09:56
 * @description
 */
public class UserComponent implements AopTestInterface {

    @Autowired
    private PersonComponent personComponent;

    public String getName(String name) {
        System.out.println("getName。。。。。。参数：" + name);
//        System.out.println(1 / 0);
        System.out.println(this);
//        this.getId();
        UserComponent userComponent = (UserComponent) AopContext.currentProxy();
        System.out.println(userComponent);
//        userComponent.getId();
        return name;
    }


    @Override
    public void getUserId() {
        System.out.println("getUserId......");
    }
}
