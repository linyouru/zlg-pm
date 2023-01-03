package com.zlg.zlgpm.pojo.bo;

public class TaskChangeListBo {

    private Integer id;
    private Integer taskId;
    private Integer uid;
    private String userName;
    private String nickName;
    private String beforeTime;
    private String time;
    private String reason;
    private String createTime;

    public TaskChangeListBo() {
    }

    public TaskChangeListBo(Integer id, Integer taskId, Integer uid, String userName, String nickName, String beforeTime, String time, String reason, String createTime) {
        this.id = id;
        this.taskId = taskId;
        this.uid = uid;
        this.userName = userName;
        this.nickName = nickName;
        this.beforeTime = beforeTime;
        this.time = time;
        this.reason = reason;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(String beforeTime) {
        this.beforeTime = beforeTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TaskChangeListBo{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", uid=" + uid +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", beforeTime='" + beforeTime + '\'' +
                ", time='" + time + '\'' +
                ", reason='" + reason + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
