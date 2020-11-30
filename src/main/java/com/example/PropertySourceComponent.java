package com.example;

import com.example.applicationEvent.MyApplicationEvent;
import com.example.demo.BBB;
import com.example.demo.CCC;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author 朱伟伟
 * @date 2020-09-04 10:24:04
 * @description
 */
@Component
//@PropertySource(value = {"classpath:test.properties"})
public class PropertySourceComponent implements InitializingBean {

    private CCC ccc;

    @Autowired
    @Qualifier(value = "CCC")
    public void sdfsfCcc(CCC ccc) {
        this.ccc = ccc;
    }

    @Value("${test.value}")
    private String testValue;

    @Autowired
    @Lazy
//    private Collection<QualifierService> qualifierServiceList;
//    private List<QualifierService> qualifierServiceList;
//    private Set<QualifierService> qualifierServiceList;
//    private Map<String, QualifierService> qualifierServiceMap;
    private List<AbstractTestService> abstractTestService;

//    @Autowired
//    @Lazy
//    public void setBbb(BBB bbb) {
//        this.bbb = bbb;
//    }

//    @Autowired
//    public void setBbb(@Lazy BBB bbb) {
//        this.bbb = bbb;
//    }

    public String getTestValue() {
        return testValue;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        System.out.println(testValue);
//        System.out.println("c............" + ccc);
//        System.out.println("qualifierService......" + qualifierService);
//        System.out.println("qualifierServiceList......" + qualifierServiceList);
//        System.out.println("qualifierServiceMap......" + qualifierServiceMap);
        System.out.println("abstractTestService......" + abstractTestService);
    }

}
