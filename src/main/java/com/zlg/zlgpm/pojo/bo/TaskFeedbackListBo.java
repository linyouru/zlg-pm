package com.zlg.zlgpm.pojo.bo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

public class TaskFeedbackListBo implements Serializable {

    private static final long serialVersionUID = 1L;

    
    private Integer id;
    private String feedback;
    private Integer uid;
    private String nickName;
    private Integer tid;
    private String remark;
    private String updateTime;
    private String createTime;

    public TaskFeedbackListBo() {
    }

    public TaskFeedbackListBo(Integer id, String feedback, Integer uid, String nickName, Integer tid, String remark, String updateTime, String createTime) {
        this.id = id;
        this.feedback = feedback;
        this.uid = uid;
        this.nickName = nickName;
        this.tid = tid;
        this.remark = remark;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
        return "TaskFeedbackListBo{" +
                "id=" + id +
                ", feedback='" + feedback + '\'' +
                ", uid=" + uid +
                ", nickName='" + nickName + '\'' +
                ", tid=" + tid +
                ", remark='" + remark + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
