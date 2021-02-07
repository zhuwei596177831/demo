package com.configurationClassPostProcessor;

import com.example.configuration.bean.TestImportBeanDefinitionRegistrar;
import com.example.configuration.bean.TestImportSelector;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * @author 朱伟伟
 * @date 2021-02-03 09:35:13
 * @description {@link ConfigurationClassPostProcessor}
 * {@link ConfigurationClassParser}
 * {@link ClassPathBeanDefinitionScanner}
 * {@link ConfigurationClassBeanDefinitionReader}
 */
@EnableAutoConfiguration
@Configuration(proxyBeanMethods = false)
@PropertySource(value = {"classpath:test.properties"})
@Import(value = {ImportClass.class, TestImportSelector.class, TestImportBeanDefinitionRegistrar.class})
@ComponentScan(basePackageClasses = {ConfigurationAnalyzeTest.class})
public class ConfigurationAnalyzeTest extends AbstractConfigurationAnalyze implements ConfigurationAnalyzeInterface {

    /**
     * 泛型依赖注入
     */
//    @Autowired
//    GenericBean<String, String> stringStringGenericBean;
//    @Autowired
//    GenericBean<Object, Object> objectObjectGenericBean;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationAnalyzeTest.class);
        applicationContext.getBean(BeanFactory.FACTORY_BEAN_PREFIX + "testFactoryBean");
        applicationContext.getBean("testFactoryBean");
//        System.out.println(applicationContext.getBean(TestMethodBean.class));
    }

    @PostConstruct
    public void init() {
//        System.out.println(stringStringGenericBean);
//        System.out.println(objectObjectGenericBean);
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

    @Bean
    GenericBean<Object, Object> objectGenericBean() {
        return new GenericBean<>("ob1", "ob2");
    }

    @Bean
    GenericBean<String, String> stringGenericBean() {
        return new GenericBean<>("str1", "str2");
    }

    @Configuration
    @PropertySource(value = {"classpath:resources.properties"})
    static class InnerConfigurationClass {

    }

    @Configuration
    static class InnerConfigurationClass1 {

    }


}
