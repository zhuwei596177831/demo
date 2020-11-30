package com.example.demo.test;

import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author 朱伟伟
 * @date 2020-11-17 17:09:33
 * @description
 */
public class TestResourcePatternResolver {
    @Test
    public void test() throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader(TestResourcePatternResolver.class.getClassLoader());
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(resourceLoader);
//        String locationPattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "META-INF/spring.factories";
        String locationPattern = ResourceLoader.CLASSPATH_URL_PREFIX + "META-INF/spring.factories";
        Resource[] resources = resourcePatternResolver.getResources(locationPattern);
    }
}
