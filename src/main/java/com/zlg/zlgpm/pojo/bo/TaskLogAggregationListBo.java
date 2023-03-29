package com.zlg.zlgpm.pojo.bo;

public class TaskLogAggregationListBo {

    private Integer id;
    private Integer uid;
    private String userName;
    private String nickName;
    private Integer taskId;
    private String name;
    private String version;
    private String log;
    private String feedback;
    private String createTime;

    public TaskLogAggregationListBo() {
    }

    public TaskLogAggregationListBo(Integer id, Integer uid, String userName, String nickName, Integer taskId, String name, String version, String log, String createTime) {
        this.id = id;
        this.uid = uid;
        this.userName = userName;
        this.nickName = nickName;
        this.taskId = taskId;
        this.name = name;
        this.version = version;
        this.log = log;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TaskLogAggregationListBo{" +
                "id=" + id +
                ", uid=" + uid +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", taskId=" + taskId +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", log='" + log + '\'' +
                ", feedback='" + feedback + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

}
