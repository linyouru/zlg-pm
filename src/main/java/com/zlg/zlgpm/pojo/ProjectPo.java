package com.zlg.zlgpm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("project")
public class ProjectPo implements Serializable {

    private static final long serialVersionUID =1L;

    @TableId(type = IdType.AUTO)
    private int id;
    @TableField
    private String name;
    @TableField
    private String version;
    @TableField
    private int uid;
    @TableField
    private String status;
    @TableField
    private String remark;
    @TableField
    private String updateTime;
    @TableField
    private String createTime;

    public ProjectPo() {
    }

    public ProjectPo(int id, String name, String version, int uid, String status, String remark) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.uid = uid;
        this.status = status;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", uid=" + uid +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

}
