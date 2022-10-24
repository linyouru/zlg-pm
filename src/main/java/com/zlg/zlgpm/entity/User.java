package com.zlg.zlgpm.entity;

import io.mybatis.provider.Entity;

import java.io.Serializable;
import java.util.Date;

@Entity.Table("user")
public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    @Entity.Column(id = true)
    private Long id;
    @Entity.Column
    private String userName;
    @Entity.Column
    private String password;
    @Entity.Column
    private String nickName;
    @Entity.Column
    private String email;
    @Entity.Column
    private String createTime;
    @Entity.Column
    private Integer status;
    @Entity.Column
    private String remark;

    public User() {
    }

    public User(Long id, String userName, String password, String nickName, String email, String createTime, Integer status, String remark) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.createTime = createTime;
        this.status = status;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }
}
