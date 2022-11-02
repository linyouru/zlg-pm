package com.zlg.zlgpm.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("user")
public class UserPo implements Serializable {

    private static final long serialVersionUID = -1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField
    private String userName;
    @TableField
    private String password;
    @TableField
    private String nickName;
    @TableField
    private String email;
    @TableField
    private String updateTime;
    @TableField
    private String createTime;
    @TableField
    private Integer status;
    @TableField
    private String remark;

    public UserPo() {
    }

    public UserPo(String userName, String password, String nickName, String email, Integer status, String remark) {
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        this.status = status;
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
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
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

    public String getUpdateTime() {
        return updateTime;
    }

    public String getCreateTime() {
        return createTime;
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

    public static UserPo.UserBuilder builder() {
        return new UserPo.UserBuilder();
    }
    
    public static class UserBuilder{

        private String userName;
        private String password;
        private String nickName;
        private String email;
        private Integer status;
        private String remark;

        public UserBuilder userName(final String userName){
            this.userName = userName;
            return this;
        }
        public UserBuilder password(final String password){
            this.password = password;
            return this;
        }
        public UserBuilder nickName(final String nickName){
            this.nickName = nickName;
            return this;
        }
        public UserBuilder email(final String email){
            this.email = email;
            return this;
        }
        public UserBuilder status(final Integer status){
            this.status = status;
            return this;
        }
        public UserBuilder remark(final String remark){
            this.remark = remark;
            return this;
        }

        public UserPo build(){
            return new UserPo(this.userName,this.password,this.nickName,this.email,this.status,this.remark);
        }
    }
}
