package com.zlg.zlgpm.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("task_log")
public class TaskLogPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField
    private Integer taskId;
    @TableField
    private Integer uid;
    @TableField
    private String workTime;
    @TableField
    private String progress;
    @TableField
    private String log;
    @TableField
    private String updateTime;
    @TableField
    private String createTime;

    public TaskLogPo() {
    }

    public TaskLogPo(Integer id, Integer taskId, Integer uid, String workTime, String progress, String log, String updateTime, String createTime) {
        this.id = id;
        this.taskId = taskId;
        this.uid = uid;
        this.workTime = workTime;
        this.progress = progress;
        this.log = log;
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

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
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
        return "TaskLogPo{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", uid=" + uid +
                ", workTime='" + workTime + '\'' +
                ", progress='" + progress + '\'' +
                ", log='" + log + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
