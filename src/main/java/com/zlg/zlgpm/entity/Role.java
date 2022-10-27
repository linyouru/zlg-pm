package com.zlg.zlgpm.entity;

import io.mybatis.provider.Entity;

import java.io.Serializable;

@Entity.Table("role")
public class Role implements Serializable {

    private static final long serialVersionUID = -2L;

    @Entity.Column(id = true)
    private int id;
    @Entity.Column
    private String name;
    @Entity.Column
    private String remark;

    public Role() {
    }

    public Role(int id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String describe) {
        this.remark = describe;
    }
}
