package com.zlg.zlgpm.pojo.bo;

/**
 * @author linyouru
 * @version 1.0
 * @date 2023/7/12 14:57:16
 */
public class StatisticTaskChangeBo {
    private Integer pid;
    private String project;
    private Integer vid;
    private String version;
    private Integer tid;
    private String task;
    private String beforeStartTime;
    private String beforeEndTime;
    private String afterStartTime;
    private String afterEndTime;
    private String reason;
    private Integer uid;
    private String nickName;
    private String auditorName;
    private String createTime;

    public StatisticTaskChangeBo() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
