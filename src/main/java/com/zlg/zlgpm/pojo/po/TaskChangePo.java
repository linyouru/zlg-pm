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
    private String beforeTime;
    @TableField
    private String time;
    @TableField
    private String reason;
    @TableField
    private String updateTime;
    @TableField
    private String createTime;

    public TaskChangePo() {
    }

    public TaskChangePo(Integer id, Integer taskId, Integer uid, String beforeTime, String time, String reason, String updateTime, String createTime) {
        this.id = id;
        this.taskId = taskId;
        this.uid = uid;
        this.beforeTime = beforeTime;
        this.time = time;
        this.reason = reason;
        this.updateTime = updateTime;
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

    public String getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(String beforeTime) {
        this.beforeTime = beforeTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    @Override
    public String toString() {
        return "TaskChangePo{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", uid=" + uid +
                ", beforeTime='" + beforeTime + '\'' +
                ", time='" + time + '\'' +
                ", reason='" + reason + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
