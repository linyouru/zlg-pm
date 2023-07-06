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
    private String workTime;
    private String progress;
    private String feedback;
    private String createTime;
    private String task;

    public TaskLogAggregationListBo() {
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
                ", workTime='" + workTime + '\'' +
                ", progress='" + progress + '\'' +
                ", feedback='" + feedback + '\'' +
                ", createTime='" + createTime + '\'' +
                ", task='" + task + '\'' +
                '}';
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
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
