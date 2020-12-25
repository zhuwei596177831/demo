package com.example.validation;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱伟伟
 * @description validator group测试
 **/
@RestController
public class ValidatorGroupController {

    /**
     * @param group:
     * @author: 朱伟伟
     * @description: 添加校验id
     **/
    @GetMapping("/add")
    public String add(@Validated Group group) {
        return "成功";
    }

    /**
     * @param group:
     * @author: 朱伟伟
     * @description: 添加校验id
     **/
    @GetMapping("/update")
    public String update(@Validated({Group.UpdateGroupClass.class}) Group group) {
        return "成功";
    }

}
