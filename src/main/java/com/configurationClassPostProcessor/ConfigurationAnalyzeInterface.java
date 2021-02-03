package com.configurationClassPostProcessor;

import org.springframework.context.annotation.Bean;

/**
 * @author 朱伟伟
 * @date 2021-02-03 14:08:27
 * @description
 */
public interface ConfigurationAnalyzeInterface {

    @Bean
    default InterfaceMethodBean interfaceMethodBean() {
        return new InterfaceMethodBean();
    }

}
