package com.zlg.zlgpm.pojo.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("permission")
public class PermissionPo implements Serializable {

    private static final long serialVersionUID = -3L;

    @TableId(type = IdType.AUTO)
    private int id;
    @TableField
    private String url;
    @TableField
    private String name;
    @TableField
    private String remark;

    public PermissionPo(int id, String url, String name, String remark) {
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
