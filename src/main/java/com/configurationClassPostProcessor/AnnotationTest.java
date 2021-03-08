package com.configurationClassPostProcessor;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 朱伟伟
 * @date 2021-02-09 10:38:53
 * @description
 */
public class AnnotationTest {

    public static void main(String[] args) {
        System.out.println(AnnotationInterface.class.getAnnotation(TestAnnotation.class));
        System.out.println(Parent.class.getAnnotation(TestAnnotation.class));
        System.out.println(Child.class.getAnnotation(TestAnnotation.class));

        TestAnnotation testAnnotation = AnnotationInterface.class.getAnnotation(TestAnnotation.class);
        System.out.println(testAnnotation);

        RequestMapping requestMapping = AnnotationUtils.getAnnotation(testAnnotation, RequestMapping.class);
        System.out.println(requestMapping);

        System.out.println(AnnotationUtils.findAnnotation(Child.class, TestAnnotation.class));
    }

    @TestAnnotation
    interface AnnotationInterface {

    }

    //    @TestAnnotation
    static class Parent implements AnnotationInterface {

    }

    static class Child extends Parent {

    }

}

