package com.example.earlyreference;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 朱伟伟
 * @date 2020-09-11 16:10:09
 * @description
 */
@Component
public class ComponentA {
    @Resource
    private ComponentB componentB;
}
