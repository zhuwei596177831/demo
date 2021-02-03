package com.configurationClassPostProcessor;

import org.springframework.context.annotation.*;

/**
 * @author 朱伟伟
 * @date 2021-02-03 09:35:13
 * @description
 */
@Configuration
public abstract class AbstractConfigurationAnalyze {

    @Bean
    AbstractConfigurationAnalyzeBean abstractConfigurationAnalyzeBean() {
        return new AbstractConfigurationAnalyzeBean();
    }

}