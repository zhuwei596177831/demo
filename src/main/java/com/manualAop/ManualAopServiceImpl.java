package com.manualAop;

import org.springframework.stereotype.Service;

/**
 * @author 朱伟伟
 * @date 2021-01-29 11:14:30
 * @description
 */
public class ManualAopServiceImpl implements  ManualAopService{
    @Override
    public String test(String name) {
        return "zww";
    }
}
