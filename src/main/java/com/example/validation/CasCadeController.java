package com.example.validation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 朱伟伟
 * @date 2020-08-05 12:00:41
 * @description 嵌套校验、list校验
 */
@RestController
public class CasCadeController {

    /**
     * @param casCade:
     * @author: 朱伟伟
     * @date: 2020-08-05 12:16
     * @description: 嵌套校验
     **/
    @PostMapping("/validateCascade")
//    public String validateCascade(@Validated @RequestBody CasCade casCade) {//成功
    public String validateCascade(@Valid @RequestBody CasCade casCade) {
        return "成功";
    }

    /**
     * @param userList:
     * @author: 朱伟伟
     * @date: 2020-08-05 12:16
     * @description: 校验body list
     **/
    @PostMapping("/validateBodyList")
//    public String validateBodyList(@RequestBody ValidList<@Valid User> userList) {//失败
    public String validateBodyList(@RequestBody @Valid ValidList<User> userList) {//成功
//    public String validateBodyList(@RequestBody @Validated ValidList<User> userList) {//成功
        return "成功";
    }

}
