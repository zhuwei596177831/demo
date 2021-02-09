package com.configurationClassPostProcessor;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 朱伟伟
 * @date 2021-02-09 09:09:59
 * @description
 */
public class TestImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinition beanDefinition = registry.getBeanDefinition("compoScanBean");
        if(beanDefinition instanceof AnnotatedBeanDefinition){
            AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) beanDefinition;
            AnnotationMetadata annotationMetadata = annotatedBeanDefinition.getMetadata();
            System.out.println(annotationMetadata);
        }
    }
}
