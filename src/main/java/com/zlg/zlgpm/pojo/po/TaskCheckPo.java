package com.zlg.zlgpm.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author linyouru
 * @version 1.0
 * @date 2023/7/10 10:59:41
 */
@TableName("task_check")
public class TaskCheckPo {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField
    private Integer taskId;
    @TableField
    private Integer type;
    @TableField
    private Integer isTimely;
    @TableField
    private String createTime;

    public TaskCheckPo() {
    }

    public TaskCheckPo(Integer id, Integer taskId, Integer type, Integer isTimely) {
        this.id = id;
        this.taskId = taskId;
        this.type = type;
        this.isTimely = isTimely;
    }

    public TaskCheckPo(Integer taskId, Integer type, Integer isTimely) {
        this.taskId = taskId;
        this.type = type;
        this.isTimely = isTimely;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsTimely() {
        return isTimely;
    }

    public void setIsTimely(Integer isTimely) {
        this.isTimely = isTimely;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TaskCheckPo{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", type=" + type +
                ", isTimely=" + isTimely +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
