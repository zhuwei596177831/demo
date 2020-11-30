package com.example.configuration;

import com.example.configuration.bean.ImportConfigurationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 朱伟伟
 * @date 2020-11-19 16:34:45
 * @description
 */
@Configuration
@ComponentScan(basePackages = {"com.example.configuration.bean.importConfigurationScanBean"})
public class ImportConfiguration {

    @Bean
    public ImportConfigurationBean importConfigurationBean() {
        return new ImportConfigurationBean();
    }


}
