package com.configurationClassPostProcessor;

import com.example.configuration.bean.TestImportBeanDefinitionRegistrar;
import com.example.configuration.bean.TestImportSelector;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;

/**
 * @author 朱伟伟
 * @date 2021-02-03 09:35:13
 * @description {@link ConfigurationClassPostProcessor}
 * {@link ConfigurationClassParser}
 * {@link ClassPathBeanDefinitionScanner}
 * {@link ConfigurationClassBeanDefinitionReader}
 */
//@EnableAutoConfiguration
@Configuration(proxyBeanMethods = false)
@PropertySource(value = {"classpath:test.properties"})
@Import(value = {ImportClass.class, TestImportSelector.class, TestImportBeanDefinitionRegistrar.class})
@ComponentScan(basePackageClasses = {ConfigurationAnalyzeTest.class})
public class ConfigurationAnalyzeTest extends AbstractConfigurationAnalyze implements ConfigurationAnalyzeInterface {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationAnalyzeTest.class);
        System.out.println(applicationContext.getBean(TestMethodBean.class));
    }

    @Bean
    TestMethodBean testMethodBean() {
        System.out.println("testMethodBean");
        return new TestMethodBean();
    }

    @Bean
    TestMethodBean1 testMethodBean1(TestMethodBean testMethodBean) {
//        testMethodBean();
        return new TestMethodBean1();
    }

    @Bean
    TestFactoryBean testFactoryBean() {
        return new TestFactoryBean();
    }


    @Configuration
    @PropertySource(value = {"classpath:resources.properties"})
    static class InnerConfigurationClass {

    }

    @Configuration
    static class InnerConfigurationClass1 {

    }


}
