package com.zlg.zlgpm.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("user_role")
public class UserRolePo implements Serializable {

    private static final long serialVersionUID = -1L;

    @TableField
    private Long uid;
    @TableField
    private Long rid;

    public UserRolePo(Long uid, Long rid) {
        this.uid = uid;
        this.rid = rid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "uid=" + uid +
                ", rid=" + rid +
                '}';
    }
}
