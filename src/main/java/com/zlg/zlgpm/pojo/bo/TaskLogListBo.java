package com.zlg.zlgpm.pojo.bo;

import com.baomidou.mybatisplus.annotation.TableField;

public class TaskLogListBo {

    private Integer id;
    private Integer taskId;
    private Integer uid;
    private String userName;
    private String nickName;
    private String workTime;
    private String progress;
    private String log;
    private String createTime;

    public TaskLogListBo() {
    }

    public TaskLogListBo(Integer id, Integer taskId, Integer uid, String userName, String nickName, String workTime, String progress, String log, String createTime) {
        this.id = id;
        this.taskId = taskId;
        this.uid = uid;
        this.userName = userName;
        this.nickName = nickName;
        this.workTime = workTime;
        this.progress = progress;
        this.log = log;
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

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    @Override
    public String toString() {
        return "TaskLogListBo{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", uid=" + uid +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", workTime='" + workTime + '\'' +
                ", progress='" + progress + '\'' +
                ", log='" + log + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
