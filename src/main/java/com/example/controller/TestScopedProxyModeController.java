package com.example.controller;

import com.example.LookUpUtils;
import com.example.PrototypeComponent;
import com.example.PrototypeInterface;
import com.example.SessionProxyMode;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱伟伟
 * @date 2020-11-06 14:05:43
 * @description
 */
@RestController
public class TestScopedProxyModeController implements InitializingBean {
//    @Autowired
//    @Lazy
//    SessionProxyMode sessionProxyMode;


    /**
     * 单例bean每次注入不同的原型bean方式：
     * 1.ApplicationContext
     * 2.@Lookup
     * 3.@lazy
     * 4.@scope设置proxyMode
     * 5.ObjectFactory、
     */
//    @Autowired
//    LookUpUtils lookUpUtils;
//    @Autowired
//    ApplicationContext applicationContext;
//    @Autowired
//    PrototypeComponent prototypeComponent;
//    @Autowired
//    @Lazy
//    PrototypeInterface prototypeInterface;
//    PrototypeComponent prototypeComponent;
//    @Autowired
//    ObjectFactory<PrototypeComponent> prototypeComponentObjectFactory;
    @Autowired
    ObjectProvider<PrototypeComponent> prototypeComponentObjectProvider;


    /**
     * @description: 不可用private static final关键字
     * 为当前类创建代理类
     **/
    @Lookup
    PrototypeComponent prototypeComponent() {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (int i = 0; i < 3; i++) {
//            System.out.println("prototypeComponent......" + prototypeComponentObjectFactory.getObject());
            System.out.println("prototypeComponent......" + prototypeComponentObjectProvider.getObject());
        }

//        System.out.println("sessionProxyMode......" + sessionProxyMode);
//        sessionProxyMode.hello();
//        System.out.println("prototypeComponent......" + prototypeComponent);
//        System.out.println("prototypeComponent......" + lookUpUtils.getPrototypeComponent());
//        System.out.println("prototypeComponent......" + prototypeComponent());
//        System.out.println("prototypeComponent......" + applicationContext.getBean(PrototypeComponent.class));
//        System.out.println("prototypeComponent......" + prototypeComponent);
//        System.out.println("prototypeComponent......" + prototypeComponent);
//        System.out.println("prototypeComponent......" + prototypeInterface);
    }
}
