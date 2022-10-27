package com.zlg.zlgpm.entity;

import io.mybatis.provider.Entity;

import java.io.Serializable;

@Entity.Table("permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = -3L;

    @Entity.Column(id = true)
    private int id;
    @Entity.Column
    private String url;
    @Entity.Column
    private String name;
    @Entity.Column
    private String remark;

    public Permission(int id, String url, String name, String remark) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
