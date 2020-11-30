package com.example.configuration.bean;

import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 朱伟伟
 * @date 2020-09-03 16:40:49
 * @description
 */
public class TestImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(TestImportBeanDefinitionRegistrarBean.class);
        registry.registerBeanDefinition("testImportBeanDefinitionRegistrarBean", rootBeanDefinition);
        //com.example.configuration.bean.TestImportBeanDefinitionRegistrarBean#0
//        String beanName = DefaultBeanNameGenerator.INSTANCE.generateBeanName(rootBeanDefinition, registry);
        //testImportBeanDefinitionRegistrarBean
        String beanName = AnnotationBeanNameGenerator.INSTANCE.generateBeanName(rootBeanDefinition, registry);
    }
}
