package com.zlg.zlgpm.pojo.bo;

public class TaskChangeListBo {

    private Integer id;
    private Integer taskId;
    private String task;
    private String projectName;
    private String projectVersion;
    private Integer uid;
    private String userName;
    private String nickName;
    private Integer auditorId;
    private String auditorName;
    private Integer status;
    private String beforeStartTime;
    private String beforeEndTime;
    private String afterStartTime;
    private String afterEndTime;
    private String reason;
    private String createTime;

    public TaskChangeListBo() {
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

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBeforeStartTime() {
        return beforeStartTime;
    }

    public void setBeforeStartTime(String beforeStartTime) {
        this.beforeStartTime = beforeStartTime;
    }

    public String getBeforeEndTime() {
        return beforeEndTime;
    }

    public void setBeforeEndTime(String beforeEndTime) {
        this.beforeEndTime = beforeEndTime;
    }

    public String getAfterStartTime() {
        return afterStartTime;
    }

    public void setAfterStartTime(String afterStartTime) {
        this.afterStartTime = afterStartTime;
    }

    public String getAfterEndTime() {
        return afterEndTime;
    }

    public void setAfterEndTime(String afterEndTime) {
        this.afterEndTime = afterEndTime;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectVersion() {
        return projectVersion;
    }

    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }

    @Override
    public String toString() {
        return "TaskChangeListBo{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", task='" + task + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectVersion='" + projectVersion + '\'' +
                ", uid=" + uid +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", auditorId=" + auditorId +
                ", auditorName='" + auditorName + '\'' +
                ", status=" + status +
                ", beforeStartTime='" + beforeStartTime + '\'' +
                ", beforeEndTime='" + beforeEndTime + '\'' +
                ", afterStartTime='" + afterStartTime + '\'' +
                ", afterEndTime='" + afterEndTime + '\'' +
                ", reason='" + reason + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
