package com.zlg.zlgpm.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("task_feedback")
public class TaskFeedbackPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField
    private String feedback;
    @TableField
    private Integer uid;
    @TableField
    private Integer tid;
    @TableField
    private String remark;
    @TableField
    private String updateTime;
    @TableField
    private String createTime;

    public TaskFeedbackPo() {
    }

    public TaskFeedbackPo(Integer id, String feedback, Integer uid, Integer tid, String remark, String updateTime, String createTime) {
        this.id = id;
        this.feedback = feedback;
        this.uid = uid;
        this.tid = tid;
        this.remark = remark;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getCreateTime() {
        return createTime;
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

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "TaskFeedbackPo{" +
                "id=" + id +
                ", feedback='" + feedback + '\'' +
                ", uid=" + uid +
                ", tid=" + tid +
                ", remark='" + remark + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
