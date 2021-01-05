package com.example.validation;


import javax.validation.constraints.NotNull;

/**
 * @author 朱伟伟
 * @description
 **/
public class Group {

    @NotNull(message = "id不能为空", groups = {UpdateGroupClass.class})
    private Long id;

    interface UpdateGroupClass {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
