package com.example.validation;

import lombok.Getter;

/**
 * @author 朱伟伟
 * @description
 **/
@Getter
public enum ResultCode {
    SUCCESS(1000, "成功"),

    FAILED(1001, "响应失败"),

    VALIDATE_FAILED(1002, "参数校验失败"),

    ERROR(5000, "未知错误");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}