package com.zlg.zlgpm.entity;

import io.mybatis.provider.Entity;

import java.io.Serializable;

@Entity.Table("user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = -1L;

    @Entity.Column("uid")
    private Long uid;
    @Entity.Column("rid")
    private Long rid;

    public UserRole(Long uid, Long rid) {
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
