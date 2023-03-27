package com.zlg.zlgpm.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zlg.zlgpm.pojo.bo.ProjectBo;

@TableName("project_version")
public class ProjectVersionPo {

    @TableId(type= IdType.AUTO)
    private Integer id;
    @TableField
    private String version;
    @TableField
    private Integer pid;
    @TableField
    private String remark;
    @TableField
    private String createTime;

    public ProjectVersionPo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "ProjectVersionPo{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", pid=" + pid +
                ", remark='" + remark + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
