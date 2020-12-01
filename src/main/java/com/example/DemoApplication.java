package com.example;

import com.example.aop.PersonComponent;
import com.example.aop.UserComponent;
import com.example.applicationEvent.MyApplicationEvent;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.origin.OriginTrackedValue;
import org.springframework.context.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.core.env.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.util.StringValueResolver;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class DemoApplication implements EmbeddedValueResolverAware, EnvironmentAware, ResourceLoaderAware, MessageSourceAware, InitializingBean {

    //    @Autowired
//    private MyApplicationListener myApplicationListener;
//    @Value("#{propertySourceComponent.testValue}")
//    private String testValue;
    @Autowired
//    @Qualifier("qualifierServiceImpl")
//    QualifierService qualifierService;
            QualifierService qualifierServiceImpl;
    //    @Autowired
//    QualifierServiceImpl qualifierService;
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    ApplicationEventMulticaster applicationEventMulticaster;
    @Autowired
    private UserComponent userComponent;
    @Autowired
    private PersonComponent personComponent;
    @Value("${server.port}")
    private Integer port;
    @Value("${test.resolver}")
    private String resolver;


    public static void main(String[] args) {
        /**
         * 1.ConfigurationClassPostProcessor
         * 2.AutowiredAnnotationBeanPostProcessor
         * 3.CommonAnnotationBeanPostProcessor
         * 4.Jpa的PersistenceAnnotationProcessor（没导包就不会注册）
         * 5.EventListenerMethodProcessor
         * 6.DefaultEventListenerFactory
         **/

        /**
         * @description: BeanPostProcessor:
         * org.springframework.context.annotation.internalConfigurationAnnotationProcessor
         * org.springframework.context.event.internalEventListenerProcessor
         * propertySourcesPlaceholderConfigurer
         * org.springframework.boot.context.properties.ConfigurationPropertiesBeanDefinitionValidator
         * preserveErrorControllerTargetClassPostProcessor
         **/

        /**
         * @description: Aop:
         * @see AnnotationAwareAspectJAutoProxyCreator
         * @see AspectJAwareAdvisorAutoProxyCreator
         * @see AbstractAdvisorAutoProxyCreator
         * @see AbstractAutoProxyCreator
         **/

        /**@see ServletRegistrationBean
         * @see FilterRegistrationBean
         * @see DelegatingFilterProxyRegistrationBean
         * @see ServletListenerRegistrationBean
         * */

        /**
         * EnvironmentAware
         * EmbeddedValueResolverAware
         * ResourceLoaderAware
         * ApplicationEventPublisherAware
         * MessageSourceAware
         * ApplicationContextAware
         **/

        /**
         * BeanNameAware
         * BeanClassLoaderAware
         * BeanFactoryAware
         **/

        /**
         * protected void initStrategies(ApplicationContext context) {
         * 		initMultipartResolver(context);
         * 		initLocaleResolver(context);
         * 		initThemeResolver(context);
         * 		initHandlerMappings(context);
         * 		initHandlerAdapters(context);
         * 		initHandlerExceptionResolvers(context);
         * 		initRequestToViewNameTranslator(context);
         * 		initViewResolvers(context);
         * 		initFlashMapManager(context);
         *        }
         *
         **/
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
//        System.out.println("applicationEventPublisher......" + applicationContext);
//        applicationContext.getEnvironment();
//        applicationContext.publishEvent(new MyApplicationEvent("朱伟伟"));
//        BeanDefinition beanDefinition = applicationContext.getBeanFactory().getBeanDefinition("scopedTarget.testScopedProxyMode");
//        BeanDefinition beanDefinition1 = applicationContext.getBeanFactory().getBeanDefinition("testScopedProxyMode");
//        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
//        springApplication.addInitializers(new MyConfigurableApplicationContext());
//        springApplication.run();

//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.example.demo");
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.example");
//        System.out.println(applicationContext.getBean(PropertySourceComponent.class).getTestValue());

//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestConfiguration.class);
//        System.out.println(applicationContext.getBean(MethodBean.class).getPort());
//        System.out.println(applicationContext.getBean(MethodBean.class).getTestValue());
//        applicationContext.getBean(EEE.class);
//        EEE eee = applicationContext.getBean(EEE.class);
//        System.out.println(eee.getMethodBean());
//        System.out.println(applicationContext.getBean(MethodBean.class).getEee());

//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.example.refreshbeantest");
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.example.earlyreference");

//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AopConfiguration.class);
//        UserComponent userComponent = applicationContext.getBean(UserComponent.class);
//        System.out.println(userComponent);
//        userComponent.getName("朱伟伟");
//        MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println(resolver.getClass().getName());
        System.out.println(resolver.resolveStringValue("${server.port}"));
        System.out.println(resolver.resolveStringValue("${test.resolver}"));
    }

    @Override
    public void setEnvironment(Environment environment) {
        /**
         * getPropertynull
         * getProperty8081
         * getPropertynull
         * resolvePlaceholders${dvdbdf.ddd}
         * resolvePlaceholders8081
         */
        System.out.println("getProperty" + environment.getProperty("${scsdcs.sds}"));
        System.out.println("getProperty" + environment.getProperty("server.port"));
        System.out.println("getProperty" + environment.getProperty("server.sss"));
        System.out.println("resolvePlaceholders" + environment.resolvePlaceholders("${dvdbdf.ddd}"));
        System.out.println("resolvePlaceholders" + environment.resolvePlaceholders("${server.port}"));
        Iterable<ConfigurationPropertySource> configurationPropertySources = ConfigurationPropertySources.get(environment);
        System.out.println(configurationPropertySources.getClass());
        Iterator<ConfigurationPropertySource> iterator = configurationPropertySources.iterator();
        while (iterator.hasNext()) {
            ConfigurationPropertySource configurationPropertySource = iterator.next();
            System.out.println(configurationPropertySource.getUnderlyingSource());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        applicationEventPublisher.publishEvent(new MyApplicationEvent("朱伟伟"));
//        applicationEventMulticaster.multicastEvent(new MyApplicationEvent("朱伟伟"));
        userComponent.getUserId();
        personComponent.getPersonId();
        System.out.println("applicationEventPublisher......" + applicationEventPublisher);
//        System.out.println("qualifierService......" + qualifierService);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("resourceLoader......" + resourceLoader.getClass().getName());
        Resource resource = resourceLoader.getResource("classpath:recources.properties");
        try {
            ResourcePropertySource resourcePropertySource = new ResourcePropertySource(resource);
            MutablePropertySources mutablePropertySources = new MutablePropertySources();
            mutablePropertySources.addLast(resourcePropertySource);
            PropertySourcesPropertyResolver propertySourcesPropertyResolver = new PropertySourcesPropertyResolver(mutablePropertySources);
            System.out.println("setResourceLoader：" + propertySourcesPropertyResolver.getProperty("test.name"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * @author: 朱伟伟
         * @date: 2020-11-10 15:05
         * @description: PropertySourceLoader
         **/
        PropertySourceLoader propertySourceLoader = new PropertiesPropertySourceLoader();
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            List<PropertySource<?>> propertySourceList = propertySourceLoader.load("recources.properties", resource);
            for (PropertySource<?> propertySource : propertySourceList) {
                Map<String, OriginTrackedValue> map = (Map<String, OriginTrackedValue>) propertySource.getSource();
                for (Map.Entry<String, OriginTrackedValue> entry : map.entrySet()) {
                    System.out.println(entry.getKey() + ":" + entry.getValue());
                }
            }
            resource = resourceLoader.getResource("classpath:recources.yaml");
            propertySourceLoader = new YamlPropertySourceLoader();
            propertySourceList = propertySourceLoader.load("recources", resource);
            for (PropertySource<?> propertySource : propertySourceList) {
                Map<String, OriginTrackedValue> map = (Map<String, OriginTrackedValue>) propertySource.getSource();
                for (Map.Entry<String, OriginTrackedValue> entry : map.entrySet()) {
                    System.out.println(entry.getKey() + ":" + entry.getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("messageSource......" + messageSource.getClass().getName());
    }
}
