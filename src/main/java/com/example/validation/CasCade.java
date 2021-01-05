package com.example.validation;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author 朱伟伟
 * @date 2020-08-05 12:01:23
 * @description
 */
public class CasCade {
    @NotNull(message = "CasCade id不能为空")
    private String id;

    @Valid
    private User user;

//    @Valid
//    private List<User> userList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
