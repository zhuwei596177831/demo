package com.example.getbean.getEarlyBeanReference;

import com.example.getbean.getEarlyBeanReference.aop.TestJdkProxy;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;

/**
 * @author 朱伟伟
 * @date 2020-11-26 17:16:02
 * @description {@link EnableAutoConfiguration} 默认：proxyTargetClass = true 开启cglib代理
 * @see org.springframework.boot.autoconfigure.aop.AopAutoConfiguration
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackageClasses = {GetEarlyBeanReferenceConfig.class})
@EnableAspectJAutoProxy(exposeProxy = true)
//@EnableAutoConfiguration
@PropertySource(value = {"classpath:recources.properties"})
public class GetEarlyBeanReferenceConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(GetEarlyBeanReferenceConfig.class);
//        applicationContext.getBean(EarlyBeanReferenceBeanA.class).test("zww");
        applicationContext.getBean(TestJdkProxy.class).test("zww");
        applicationContext.getBean(PrototypeEarlyBean.class);
//        applicationContext.getBean(RequestEarlyBean.class);
        applicationContext.close();
    }

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public InitMethodBean initMethodBean() {
        return new InitMethodBean();
    }


}
