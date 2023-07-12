package com.zlg.zlgpm.pojo.bo;

/**
 * @author linyouru
 * @version 1.0
 * @date 2023/7/12 13:30:59
 */
public class StatisticFeedbackBo {
    private Integer pid;
    private String project;
    private Integer vid;
    private String version;
    private Integer tid;
    private String task;
    private String feedback;
    private Integer uid;
    private String nickName;
    private Integer accepterUid;
    private String accepterNickName;
    private String createTime;

    public StatisticFeedbackBo() {
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
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

    public Integer getAccepterUid() {
        return accepterUid;
    }

    public void setAccepterUid(Integer accepterUid) {
        this.accepterUid = accepterUid;
    }

    public String getAccepterNickName() {
        return accepterNickName;
    }

    public void setAccepterNickName(String accepterNickName) {
        this.accepterNickName = accepterNickName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
