package com.zlg.zlgpm.entity;

import java.io.Serializable;

public class Permission implements Serializable {

    private static final long serialVersionUID = -3L;

    private int id;
    private String url;
    private String name;
    private String describe;

    public Permission(int id, String url, String name, String describe) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
