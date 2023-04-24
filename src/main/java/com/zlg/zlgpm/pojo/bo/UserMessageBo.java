package com.zlg.zlgpm.pojo.bo;

public class UserMessageBo {

    private Integer id;
    private String userName;
    private String nickname;
    /**
     * 待验收数
     */
    private Integer acceptNum;
    /**
     * 待审核数
     */
    private Integer auditNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAcceptNum() {
        return acceptNum;
    }

    public void setAcceptNum(Integer acceptNum) {
        this.acceptNum = acceptNum;
    }

    public Integer getAuditNum() {
        return auditNum;
    }

    public void setAuditNum(Integer auditNum) {
        this.auditNum = auditNum;
    }

    @Override
    public String toString() {
        return "UserMessageBo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", acceptNum=" + acceptNum +
                ", auditNum=" + auditNum +
                '}';
    }
}
