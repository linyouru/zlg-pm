package com.zlg.zlgpm.pojo.bo;

/**
 * 任务列表实体
 * @author linyouru
 */
public class TaskListBo {

    private Integer id;
    private String projectName;
    private String projectVersion;
    private String taskType;
    private Integer pid;
    private String task;
    private String status;
    private String nickName;
    private String playStartTime;
    private String playEndTime;
    private String timely;
    private String quality;
    private String document;
    private String remark;
    private String link;
    private String module;
    private String updateTime;
    private String createTime;
    private String overtime;
    private String warning;
    private String workTimeCount;
    private String progress;
    private Integer haveDocument;

    public TaskListBo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPlayStartTime() {
        return playStartTime;
    }

    public void setPlayStartTime(String playStartTime) {
        this.playStartTime = playStartTime;
    }

    public String getPlayEndTime() {
        return playEndTime;
    }

    public void setPlayEndTime(String playEndTime) {
        this.playEndTime = playEndTime;
    }

    public String getTimely() {
        return timely;
    }

    public void setTimely(String timely) {
        this.timely = timely;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getWorkTimeCount() {
        return workTimeCount;
    }

    public void setWorkTimeCount(String workTimeCount) {
        this.workTimeCount = workTimeCount;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Integer getHaveDocument() {
        return haveDocument;
    }

    public void setHaveDocument(Integer haveDocument) {
        this.haveDocument = haveDocument;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "TaskListBo{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", projectVersion='" + projectVersion + '\'' +
                ", taskType='" + taskType + '\'' +
                ", pid='" + pid + '\'' +
                ", task='" + task + '\'' +
                ", status='" + status + '\'' +
                ", nickName='" + nickName + '\'' +
                ", playStartTime='" + playStartTime + '\'' +
                ", playEndTime='" + playEndTime + '\'' +
                ", timely='" + timely + '\'' +
                ", quality='" + quality + '\'' +
                ", document='" + document + '\'' +
                ", remark='" + remark + '\'' +
                ", link='" + link + '\'' +
                ", module='" + module + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", overtime='" + overtime + '\'' +
                ", warning='" + warning + '\'' +
                ", workTimeCount='" + workTimeCount + '\'' +
                ", progress='" + progress + '\'' +
                ", haveDocument=" + haveDocument +
                '}';
    }
}
