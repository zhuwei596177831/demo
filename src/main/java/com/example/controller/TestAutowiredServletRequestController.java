package com.example.controller;

import com.example.QualifierService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

/**
 * @author 朱伟伟
 * @date 2020-11-09 17:36:57
 * @description
 */
@RestController
public class TestAutowiredServletRequestController implements InitializingBean {

    @Autowired
    @Qualifier("qualifierServiceImpl")
    QualifierService qualifierService;

    @Autowired
    ServletRequest servletRequest;

    @GetMapping("/testAutowiredServletRequest")
    public void testAutowiredServletRequest() {
        System.out.println(servletRequest.getParameter("id"));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("qualifierService......" + qualifierService);
    }
}
