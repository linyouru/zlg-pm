package com.zlg.zlgpm.pojo.bo;


public class UserListBo {

    private Long id;
    private String userName;
    private String password;
    private String nickName;
    private String email;
    private String updateTime;
    private String createTime;
    private Integer status;
    private String remark;
    private String custom;
    private String taskTotal;
    private String taskFinishCount;
    private String taskTimelyCount;
    private String taskTimeoutCount;
    private String timelinessRate;
    private Integer position;

    public UserListBo() {
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }

    public String getTaskTotal() {
        return taskTotal;
    }

    public void setTaskTotal(String taskTotal) {
        this.taskTotal = taskTotal;
    }

    public String getTaskFinishCount() {
        return taskFinishCount;
    }

    public void setTaskFinishCount(String taskFinishCount) {
        this.taskFinishCount = taskFinishCount;
    }

    public String getTaskTimelyCount() {
        return taskTimelyCount;
    }

    public void setTaskTimelyCount(String taskTimelyCount) {
        this.taskTimelyCount = taskTimelyCount;
    }

    public String getTaskTimeoutCount() {
        return taskTimeoutCount;
    }

    public void setTaskTimeoutCount(String taskTimeoutCount) {
        this.taskTimeoutCount = taskTimeoutCount;
    }

    public String getTimelinessRate() {
        return timelinessRate;
    }

    public void setTimelinessRate(String timelinessRate) {
        this.timelinessRate = timelinessRate;
    }

    @Override
    public String toString() {
        return "UserListBo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", custom='" + custom + '\'' +
                ", taskTotal='" + taskTotal + '\'' +
                ", taskFinishCount='" + taskFinishCount + '\'' +
                ", taskTimelyCount='" + taskTimelyCount + '\'' +
                ", taskTimeoutCount='" + taskTimeoutCount + '\'' +
                ", timelinessRate='" + timelinessRate + '\'' +
                '}';
    }
}
