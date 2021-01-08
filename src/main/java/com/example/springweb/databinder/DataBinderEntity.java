package com.example.springweb.databinder;

import com.example.generic.BaseEntity;

/**
 * @author 朱伟伟
 * @date 2021-01-08 13:54:57
 * @description
 */
public class DataBinderEntity extends BaseEntity {
    private static final long serialVersionUID = 1741658255868114701L;
    private String name;
    private Integer age;
    private Boolean flag;
    private DataBinderInnerEntity dataBinderInnerEntity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public DataBinderInnerEntity getDataBinderInnerEntity() {
        return dataBinderInnerEntity;
    }

    public void setDataBinderInnerEntity(DataBinderInnerEntity dataBinderInnerEntity) {
        this.dataBinderInnerEntity = dataBinderInnerEntity;
    }
}
