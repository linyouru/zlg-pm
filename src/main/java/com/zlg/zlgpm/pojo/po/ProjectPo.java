package com.zlg.zlgpm.pojo.po;

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
    private Integer uid;
    @TableField
    private String status;
    @TableField
    private String remark;
    @TableField
    private String link;
    @TableField
    private String updateTime;
    @TableField
    private String createTime;

    public ProjectPo() {
    }

    @Override
    public String toString() {
        return "ProjectPo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uid=" + uid +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", link='" + link + '\'' +
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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
