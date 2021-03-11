package com.example.mianshi.designpatterns.statemode.normal;

/**
 * @author 朱伟伟
 * @date 2021-03-11 15:11:18
 * @description
 */
public class ActivityInfoResult {
    private String code;
    private String msg;

    public ActivityInfoResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ActivityInfoResult{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
