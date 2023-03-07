package com.zlg.zlgpm.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("user_project")
public class UserProjectPo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField
    private Integer uid;
    @TableField
    private Integer pid;

    public UserProjectPo(Integer uid, Integer pid) {
        this.uid = uid;
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "UserProjectPo{" +
                "uid=" + uid +
                ", pid=" + pid +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
