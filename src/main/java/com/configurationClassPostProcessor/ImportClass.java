package com.configurationClassPostProcessor;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author 朱伟伟
 * @date 2021-02-03 11:04:12
 * @description
 */
public class ImportClass {
//    @Autowired
//    GenericBean<String, String> stringStringGenericBean;
//    @Autowired
//    GenericBean<Object, Object> objectObjectGenericBean;

    @Resource
    ComponentScanBean scanBean;

    @PostConstruct
    public void init(){

    }

}
