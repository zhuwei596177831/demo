package com.example.demo.test;

import org.junit.Test;
import org.springframework.boot.context.properties.bind.PropertySourcesPlaceholdersResolver;
import org.springframework.core.env.*;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 朱伟伟
 * @date 2020-10-29 15:08:08
 * @description
 */
public class PropertyResolverTest {
    @Test
    public void testPropertyPlaceholderHelper() {
        PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper("${", "}", ":", true);
        propertyPlaceholderHelper.replacePlaceholders("${test.name:zww}", this::resolvePlaceholder);
    }

    private String resolvePlaceholder(String placeHolder) {
        return null;
    }

    @Test
    public void testPropertyResolver() throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("encoding", "gbk");
        MapPropertySource mapPropertySource = new MapPropertySource("mapPropertySource", map);
        System.out.println(mapPropertySource.getProperty("encoding"));
        ResourcePropertySource resourcePropertySource = new ResourcePropertySource("resourcePropertySource", "classpath:recources.properties");
        System.out.println(resourcePropertySource.getProperty("test.name"));
        MutablePropertySources mutablePropertySources = new MutablePropertySources();
        mutablePropertySources.addFirst(mapPropertySource);
        mutablePropertySources.addLast(resourcePropertySource);
        System.out.println(mutablePropertySources.get("resourcePropertySource").getProperty("test.name"));
        PropertyResolver propertyResolver = new PropertySourcesPropertyResolver(mutablePropertySources);
        System.out.println(propertyResolver.getProperty("encoding"));
        System.out.println(propertyResolver.resolvePlaceholders("dsdcs ..s.ds ${test.name}"));
    }

    @Test
    public void testPlaceholdersResolver() throws IOException {
        PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper(
                SystemPropertyUtils.PLACEHOLDER_PREFIX, SystemPropertyUtils.PLACEHOLDER_SUFFIX, SystemPropertyUtils.VALUE_SEPARATOR, true);
        ResourcePropertySource resourcePropertySource = new ResourcePropertySource("resourcePropertySource", "classpath:recources.properties");
        MutablePropertySources mutablePropertySources = new MutablePropertySources();
        mutablePropertySources.addLast(resourcePropertySource);
        PropertySourcesPlaceholdersResolver propertySourcesPlaceholdersResolver = new PropertySourcesPlaceholdersResolver(
                mutablePropertySources, propertyPlaceholderHelper);
        System.out.println(propertySourcesPlaceholdersResolver.resolvePlaceholders("${test.name:zww}"));//朱伟伟
        System.out.println(propertySourcesPlaceholdersResolver.resolvePlaceholders("${test.id}"));//${test.id}
    }


    @Test
    public void testEnvironment() {
//        StandardEnvironment environment = new StandardEnvironment();
//        environment.getSystemProperties().forEach((s, o) -> {
//            System.out.println(s + ":" + o);
//        });
//        environment.getSystemEnvironment().forEach((s, o) -> {
//            System.out.println(s + ":" + o);
//        });
    }

    @Test
    public void testDifferentBetweenGetPropertyAndResolvePlaceHolders() {
        StandardEnvironment environment = new StandardEnvironment();
        environment.setIgnoreUnresolvableNestedPlaceholders(true);
        MutablePropertySources mutablePropertySources = environment.getPropertySources();
        MapPropertySource mapPropertySource = new MapPropertySource("diy", new HashMap<String, Object>() {
            private static final long serialVersionUID = 4097313322845791976L;

            {
                put("app.name", "fsx");
                put("app.key", "${user.home1}"); // 注意这里是user.home1 特意让系统属性里不存在的
                put("app.full", "${app.key} + ${app.name}");
            }
        });
        mutablePropertySources.addFirst(mapPropertySource);


        // 正常使用
        String s = environment.resolvePlaceholders("${app.full}");
        System.out.println(s);

        s = environment.getProperty("app.full");
//        s = environment.getProperty("app.name");
        System.out.println(s);
    }

}
