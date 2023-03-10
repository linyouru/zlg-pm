package com.zlg.zlgpm.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("task_change")
public class TaskChangePo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField
    private Integer taskId;
    @TableField
    private Integer uid;
    @TableField
    private Integer auditorId;
    @TableField
    private String auditorName;
    @TableField
    private Integer status;
    @TableField
    private String beforeStartTime;
    @TableField
    private String beforeEndTime;
    @TableField
    private String afterStartTime;
    @TableField
    private String afterEndTime;
    @TableField
    private String reason;
    @TableField
    private String updateTime;
    @TableField
    private String createTime;

    public TaskChangePo() {
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
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

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    @Override
    public String toString() {
        return "TaskChangePo{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", uid=" + uid +
                ", auditorId=" + auditorId +
                ", auditorName=" + auditorName +
                ", status=" + status +
                ", beforeStartTime='" + beforeStartTime + '\'' +
                ", beforeEndTime='" + beforeEndTime + '\'' +
                ", afterStartTime='" + afterStartTime + '\'' +
                ", afterEndTime='" + afterEndTime + '\'' +
                ", reason='" + reason + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
