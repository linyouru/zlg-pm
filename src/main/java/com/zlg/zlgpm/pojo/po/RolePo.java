package com.zlg.zlgpm.pojo.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("role")
public class RolePo implements Serializable {

    private static final long serialVersionUID = -2L;

    @TableId(type = IdType.AUTO)
    private int id;
    @TableField
    private String name;
    @TableField
    private String remark;

    public RolePo() {
    }

    public RolePo(int id, String name, String remark) {
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
