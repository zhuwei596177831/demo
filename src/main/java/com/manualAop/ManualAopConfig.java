package com.manualAop;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 朱伟伟
 * @date 2021-01-29 11:15:28
 * @description
 */
@Configuration(proxyBeanMethods = false)
public class ManualAopConfig {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ManualAopConfig.class);
//        applicationContext.getBean(ManualAopService.class).test("sd");
        applicationContext.getBean("manualAopServiceAopProxy", ManualAopService.class).test("sss");
    }

    @Bean
    ManualAopAdviceComposite manualAopAdviceComposite() {
        return new ManualAopAdviceComposite();
    }

    @Bean
    ManualAopServiceImpl manualAopService() {
        return new ManualAopServiceImpl();
    }

    /**
     * @author: 朱伟伟
     * @date: 2021-01-29 11:22
     * @description: aop方式一: {@link BeanNameAutoProxyCreator}
     **/
//    @Bean
//    BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
//        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
//        beanNameAutoProxyCreator.setBeanNames("manualAopService");
//        beanNameAutoProxyCreator.setInterceptorNames("manualAopAdviceComposite");
//        //强制使用cglib
//        beanNameAutoProxyCreator.setProxyTargetClass(true);
//        return beanNameAutoProxyCreator;
//    }

    /**
     * @param manualAopService:
     * @author: 朱伟伟
     * @date: 2021-01-29 13:40
     * @description: aop方式二: {@link ProxyFactoryBean}
     **/
    @Bean
    ProxyFactoryBean manualAopServiceAopProxy(ManualAopService manualAopService) {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setProxyTargetClass(true);
        //指定接口
        proxyFactoryBean.setInterfaces(ManualAopService.class);
        //指定target
        proxyFactoryBean.setTarget(manualAopService);
        //指定advice
        proxyFactoryBean.setInterceptorNames("manualAopAdviceComposite");
        return proxyFactoryBean;
    }


}
