package com.zlg.zlgpm.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("task_relevance")
public class TaskRelevancePo {

    @TableField
    private Integer tid;
    @TableField
    private Integer vid;

    public TaskRelevancePo() {
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }
}
