package com.example.configuration.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 朱伟伟
 * @date 2020-09-03 16:39:14
 * @description
 */
public class TestImportSelector implements ImportSelector, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware, ResourceLoaderAware {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.example.configuration.bean.TestImportSelectorBean"};
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println(classLoader.getClass().getName());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println(beanFactory.getClass().getName());
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println(environment.getClass().getName());
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println(resourceLoader.getClass().getName());
    }
}
