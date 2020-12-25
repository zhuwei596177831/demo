package com.example.validation;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @author 朱伟伟
 * @date 2020-09-27 10:36:21
 * @description
 */
@RestController
@RequestMapping("/test")
@Validated
public class TestController {

    @GetMapping("/testValidated")
    public Result testValidated(@Validated({TestValidated.TestValidatedInterface.class}) TestValidated testValidated) {
//    public Result testValidated(TestValidated testValidated) {
//        public Result testValidated (@NotEmpty String name){
        return new Result(null);
    }

    @GetMapping("/testMethod")
    public Result testMethod(@NotEmpty String name) {
        return new Result(null);
    }

    @GetMapping("/testList")
//    public Result testList(@NotEmpty ValidList<String> list) {
    public Result testList(@NotEmpty @RequestBody(required = false) @Valid ValidList<TestValidated> testValidatedList) {
//    public Result testList(@NotEmpty @RequestBody(required = false) ValidList<@Valid TestValidated> testValidatedList) {
        return new Result(null);
    }

}
