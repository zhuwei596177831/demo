package com.noxmlweb;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱伟伟
 * @date 2021-02-01 11:24:10
 * @description
 */
@Configuration(proxyBeanMethods = false)
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class, ControllerAdvice.class, RestController.class}),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {ServletConfig.class})
})
public class RootConfig {
}
