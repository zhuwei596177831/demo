package com.example.configuration;

import com.example.configuration.bean.MethodBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 朱伟伟
 * @date 2020-09-08 15:59:48
 * @description
 */
public class EEE {
    @Autowired
    private MethodBean methodBean;

    public MethodBean getMethodBean() {
        return methodBean;
    }

    public void setMethodBean(MethodBean methodBean) {
        this.methodBean = methodBean;
    }

}
