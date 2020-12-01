package com.example.getbean.getEarlyBeanReference;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 朱伟伟
 * @date 2020-12-01 17:37:09
 * @description
 */
@EnableAspectJAutoProxy(exposeProxy = true)
@Configuration
public class ExposeProxyConfig {
}
