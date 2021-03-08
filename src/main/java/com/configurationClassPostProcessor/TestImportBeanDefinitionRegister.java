package com.configurationClassPostProcessor;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2021-02-09 09:09:59
 * @description
 */
public class TestImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinition beanDefinition = registry.getBeanDefinition("compoScanBean");
        if (beanDefinition instanceof AnnotatedBeanDefinition) {
            AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) beanDefinition;
            AnnotationMetadata annotationMetadata = annotatedBeanDefinition.getMetadata();
            System.out.println(annotationMetadata);
        }
        Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(ComponentScan.class.getName(), true);

        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(ComponentScan.class.getName(), true));


        CachingMetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();
        try {
            MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(new ClassPathResource("com/configurationClassPostProcessor/ConfigurationAnalyzeTest.class"));
            ClassMetadata classMetadata = metadataReader.getClassMetadata();
            AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
            Resource resource = metadataReader.getResource();
            System.out.println(metadataReader.getClass());

            metadataReader = metadataReaderFactory.getMetadataReader(ConfigurationAnalyzeTest.class.getName());
            classMetadata = metadataReader.getClassMetadata();
            annotationMetadata = metadataReader.getAnnotationMetadata();
            resource = metadataReader.getResource();
            System.out.println(metadataReader.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
