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
    private String detail;
    @TableField
    private String status;
    @TableField
    private Integer uid;
    @TableField
    private Integer pid;
    @TableField
    private Integer vid;
    @TableField
    private Integer createdUid;
    @TableField
    private String createdUserNickname;
    @TableField
    private Integer accepterId;
    @TableField
    private Integer level;
    @TableField
    private String playStartTime;
    @TableField
    private String playEndTime;
    @TableField
    private String acceptanceTime;
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
    private Integer mid;
    @TableField
    private Integer haveDocument;
    @TableField
    private Integer serialNumber;
    @TableField
    private String updateTime;
    @TableField
    private String createTime;

    public TaskPo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getAccepterId() {
        return accepterId;
    }

    public void setAccepterId(Integer accepterId) {
        this.accepterId = accepterId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
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

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
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

    public Integer getCreatedUid() {
        return createdUid;
    }

    public void setCreatedUid(Integer createdUid) {
        this.createdUid = createdUid;
    }

    public String getAcceptanceTime() {
        return acceptanceTime;
    }

    public void setAcceptanceTime(String acceptanceTime) {
        this.acceptanceTime = acceptanceTime;
    }

    public String getCreatedUserNickname() {
        return createdUserNickname;
    }

    public void setCreatedUserNickname(String createdUserNickname) {
        this.createdUserNickname = createdUserNickname;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
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
                ", vid=" + vid +
                ", createdUid=" + createdUid +
                ", createdUserNickname='" + createdUserNickname + '\'' +
                ", playStartTime='" + playStartTime + '\'' +
                ", playEndTime='" + playEndTime + '\'' +
                ", acceptanceTime='" + acceptanceTime + '\'' +
                ", timely='" + timely + '\'' +
                ", quality='" + quality + '\'' +
                ", document='" + document + '\'' +
                ", remark='" + remark + '\'' +
                ", link='" + link + '\'' +
                ", mid=" + mid +
                ", haveDocument=" + haveDocument +
                ", serialNumber=" + serialNumber +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
