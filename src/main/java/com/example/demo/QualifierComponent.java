package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2020-09-02 10:27:50
 * @description
 */
@Component
public class QualifierComponent {
    @Autowired
    @Qualifier(value = "qualifierInterfaceImpl1")
    QualifierInterface qualifierInterface;
}
