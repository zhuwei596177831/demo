package com.example.configuration.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringValueResolver;

/**
 * @author 朱伟伟
 * @date 2020-09-04 17:53:55
 * @description
 */
public class BeanMethod implements EmbeddedValueResolverAware, EnvironmentAware {
    @Autowired
    private MethodBean methodBean;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("test.value......" + resolver.resolveStringValue("${test.value}"));
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("test.value......" + environment.resolvePlaceholders("${test.value}"));
    }
}
