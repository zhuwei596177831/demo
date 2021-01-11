package com.example.configuration.scan;

import com.example.configuration.bean.MethodBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-09-03 16:56:42
 * @description
 */
@Component
//@ComponentScan(basePackages = {"com.example.configuration.bean.importConfigurationScanBean"})
//@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
@PropertySource(value = {"classpath:test.properties", "classpath:resources.properties"})
public class TestScanBean implements InitializingBean {
    @Value("${test.value}")
    private String testValue;

    private MethodBean methodBean;

    public TestScanBean(MethodBean methodBean) {
        this.methodBean = methodBean;
    }

//    public TestScanBean() {
//        System.out.println("TestScanBean's constructor=============");
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("testValue......" + testValue);
    }
}
