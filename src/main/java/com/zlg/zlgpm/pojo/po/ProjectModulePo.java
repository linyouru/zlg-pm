package com.zlg.zlgpm.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("project_modules")
public class ProjectModulePo implements Serializable {

    private static final long serialVersionUID =1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField
    private Integer pid;
    @TableField
    private String module;
    @TableField
    private Integer level;


    public ProjectModulePo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "ProjectModulePo{" +
                "id=" + id +
                ", pid=" + pid +
                ", module='" + module + '\'' +
                ", level=" + level +
                '}';
    }
}
