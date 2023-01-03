package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.pojo.bo.TaskChangeListBo;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.po.TaskChangePo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskChangeMapper extends BaseMapper<TaskChangePo> {

    @Select("SELECT t.id,\n" +
            "         t.taskId,\n" +
            "         t.uid,\n" +
            "         u.userName,\n" +
            "         u.nickName,\n" +
            "         t.beforeTime,\n" +
            "         t.time,\n" +
            "         t.reason,\n" +
            "         t.createTime\n" +
            "FROM `task_change` AS t\n" +
            "LEFT JOIN `user` AS u\n" +
            "    ON t.uid = u.id \n" +
            "${ew.customSqlSegment}")
    Page<TaskChangeListBo> selectPage(Page<TaskChangeListBo> taskChangeListBoPage, @Param(Constants.WRAPPER) Wrapper ew);

}
