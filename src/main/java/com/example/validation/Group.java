package com.example.validation;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 朱伟伟
 * @description
 **/
@Data
public class Group {

    @NotNull(message = "id不能为空", groups = {UpdateGroupClass.class})
    private Long id;

    interface UpdateGroupClass {

    }

}
