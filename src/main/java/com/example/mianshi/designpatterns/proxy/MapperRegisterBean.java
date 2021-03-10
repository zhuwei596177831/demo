package com.example.mianshi.designpatterns.proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

/**
 * @author 朱伟伟
 * @date 2021-03-10 15:27:23
 * @description
 */
@Component
public class MapperRegisterBean implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(MyMapperFactoryBean.class);
        genericBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue(TestMapper.class);
        BeanNameGenerator beanNameGenerator = new DefaultBeanNameGenerator();
        registry.registerBeanDefinition(beanNameGenerator.generateBeanName(genericBeanDefinition, registry), genericBeanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
