package com.example.springweb.controller;

import com.example.generic.ArrayData;
import com.example.generic.BaseEntity;
import com.example.generic.Result;
import com.example.springweb.support.MethodDesc;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 朱伟伟
 * @date 2021-05-05 18:30:37
 * @description
 */
@RestController
@RequestMapping("/axios")
public class AxiosController {

    //    @GetMapping("/getOrganList")
    @PostMapping("/getOrganList")
    public Result<ArrayData<Organ>> getOrganList(@RequestBody String data, @RequestBody String page) {
//    public Result<ArrayData<Organ>> getOrganList(@RequestParam String username,
//                                                 @RequestParam String password,
//                                                 MultipartFile picFile) {
        return Result.ok(Organ.ORGANS);
    }

    @GetMapping("/getOrgan")
    public Result<Organ> getOrgan() {
        return Result.ok(Organ.INSTANCE);
    }

}

class Organ extends BaseEntity {

    private static final long serialVersionUID = -5712760029160024114L;
    public static final List<Organ> ORGANS;
    public static final Organ INSTANCE = new Organ("哈哈哈", 26, new BigDecimal("5514.55"), true);

    static {
        ORGANS = new LinkedList<>();
        ORGANS.add(new Organ("朱伟伟", 26, new BigDecimal("7500.55"), true));
        ORGANS.add(new Organ("美月恋", 24, new BigDecimal("4410.55"), false));
    }

    public Organ() {
    }

    public Organ(String name, Integer age, BigDecimal mount, Boolean flag) {
        this.name = name;
        this.age = age;
        this.mount = mount;
        this.flag = flag;
        this.localDateTime = LocalDateTime.now();
        this.localDate = LocalDate.now();
        this.localTime = LocalTime.now();
        this.date = new Date();
    }

    private String name;
    private Integer age;
    private BigDecimal mount;
    private Boolean flag;
    private LocalDateTime localDateTime;
    private LocalDate localDate;
    private LocalTime localTime;
    private Date date;

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

    public BigDecimal getMount() {
        return mount;
    }

    public void setMount(BigDecimal mount) {
        this.mount = mount;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
