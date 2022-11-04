package com.zlg.zlgpm.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("Operation_log")
public class OperationLogPo {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField
    private Integer uid;
    @TableField
    private String record;
    @TableField
    private String createTime;

    public OperationLogPo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                "id=" + id +
                ", uid=" + uid +
                ", record='" + record + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
