package com.example.validation;

import javax.validation.constraints.NotEmpty;

/**
 * @author 朱伟伟
 * @date 2020-09-27 10:41:59
 * @description
 */
public class TestValidated {
    @NotEmpty(message = "打发打发", groups = {TestValidatedInterface.class})
//    @NotEmpty(message = "打发打发")
    private String id;
    @NotEmpty(groups = {TestValidatedInterface.class})
//    @NotEmpty
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public interface TestValidatedInterface {

    }
}
