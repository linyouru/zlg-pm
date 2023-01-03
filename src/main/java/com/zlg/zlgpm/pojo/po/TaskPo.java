package com.zlg.zlgpm.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("project_task")
public class TaskPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField
    private String taskType;
    @TableField
    private String task;
    @TableField
    private String status;
    @TableField
    private Integer uid;
    @TableField
    private Integer pid;
    @TableField
    private String playStartTime;
    @TableField
    private String playEndTime;
    @TableField
    private String timely;
    @TableField
    private String quality;
    @TableField
    private String document;
    @TableField
    private String remark;
    @TableField
    private String link;
    @TableField
    private String module;
    @TableField
    private Integer haveDocument;
    @TableField
    private String updateTime;
    @TableField
    private String createTime;

    public TaskPo() {
    }

    public TaskPo(Integer id, String taskType, String task, String status, Integer uid, Integer pid, String playStartTime, String playEndTime, String timely, String quality, String document, String remark, String link, String module,Integer haveDocument, String updateTime, String createTime) {
        this.id = id;
        this.taskType = taskType;
        this.task = task;
        this.status = status;
        this.uid = uid;
        this.pid = pid;
        this.playStartTime = playStartTime;
        this.playEndTime = playEndTime;
        this.timely = timely;
        this.quality = quality;
        this.document = document;
        this.remark = remark;
        this.link = link;
        this.module = module;
        this.haveDocument = haveDocument;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public Integer getHaveDocument() {
        return haveDocument;
    }

    public void setHaveDocument(Integer haveDocument) {
        this.haveDocument = haveDocument;
    }

    @Override
    public String toString() {
        return "TaskPo{" +
                "id=" + id +
                ", taskType='" + taskType + '\'' +
                ", task='" + task + '\'' +
                ", status='" + status + '\'' +
                ", uid=" + uid +
                ", pid=" + pid +
                ", playStartTime='" + playStartTime + '\'' +
                ", playEndTime='" + playEndTime + '\'' +
                ", timely='" + timely + '\'' +
                ", quality='" + quality + '\'' +
                ", document='" + document + '\'' +
                ", remark='" + remark + '\'' +
                ", link='" + link + '\'' +
                ", module='" + module + '\'' +
                ", haveDocument='" + haveDocument + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
