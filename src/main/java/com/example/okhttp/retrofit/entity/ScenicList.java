package com.example.okhttp.retrofit.entity;

import java.io.Serializable;

/**
 * @author 朱伟伟
 * @date 2020-12-18 00:21:52
 * @description
 */
public class ScenicList implements Serializable {
    private static final long serialVersionUID = 3954649892268172253L;
    private String id;
    private String thumb;
    private String description;
    private String business_line;
    private String position;
    private String title;
    private String map;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusiness_line() {
        return business_line;
    }

    public void setBusiness_line(String business_line) {
        this.business_line = business_line;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
