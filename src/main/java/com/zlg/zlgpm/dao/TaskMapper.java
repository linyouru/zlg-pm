package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.pojo.bo.ProjectBo;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskMapper extends BaseMapper<TaskPo> {


    @Select("SELECT t.id,\n" +
            "         p.`name` AS projectName,\n" +
            "         p.version AS projectVersion,\n" +
            "         t.taskType,\n" +
            "         t.task,\n" +
            "         t.`status`,\n" +
            "         u.nickName,\n" +
            "         t.playStartTime,\n" +
            "         t.playEndTime,\n" +
            "         t.startTime,\n" +
            "         t.endTime,\n" +
            "         t.timely,\n" +
            "         t.quality,\n" +
            "         t.document,\n" +
            "         t.remark,\n" +
            "         t.link,\n" +
            "         t.updateTime,\n" +
            "         t.createTime,\n" +
            "         UNIX_TIMESTAMP() * 1000 - t.playEndTime > 0 AS overtime,\n" +
            "         t.playEndTime - UNIX_TIMESTAMP() * 1000 BETWEEN 0 AND 86400000 AS warning\n"+
            "FROM `project_task` AS t\n" +
            "LEFT JOIN `project` AS p\n" +
            "    ON t.pid = p.id\n" +
            "LEFT JOIN `user` AS u\n" +
            "    ON t.uid = u.id\n" +
            "${ew.customSqlSegment}\n" +
            "ORDER BY  p.`name`,p.version,t.playStartTime")
    Page<TaskListBo> selectPage(Page<TaskListBo> taskListBoPage, @Param(Constants.WRAPPER) Wrapper ew);


}
