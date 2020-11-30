package com.example.configuration.bean;

import com.example.configuration.EEE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 朱伟伟
 * @date 2020-09-03 17:16:48
 * @description
 */
public class MethodBean {

    @Value("${test.value}")
    private String testValue;
    @Value("${server.port}")
    private String port;

    @Autowired
    private EEE eee;

    public EEE getEee() {
        return eee;
    }

    public void setEee(EEE eee) {
        this.eee = eee;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
