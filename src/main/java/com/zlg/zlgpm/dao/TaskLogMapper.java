package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.pojo.bo.TaskLogAggregationListBo;
import com.zlg.zlgpm.pojo.bo.TaskLogListBo;
import com.zlg.zlgpm.pojo.po.TaskLogPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskLogMapper extends BaseMapper<TaskLogPo> {

    @Select("SELECT t.id,\n" +
            "        t.taskId,\n" +
            "        t.uid,\n" +
            "        u.userName,\n" +
            "        u.nickName,\n" +
            "        t.workTime,\n" +
            "        t.progress,\n" +
            "        t.log,\n" +
            "        t.createTime\n" +
            "FROM `task_log` AS t\n" +
            "LEFT JOIN `user` AS u\n" +
            "    ON t.uid = u.id\n" +
            "${ew.customSqlSegment}")
    Page<TaskLogListBo> selectPage(Page<TaskLogListBo> taskLogListBoPage, @Param(Constants.WRAPPER) Wrapper ew);

    @Select("SELECT tl.id, tl.uid, u.userName, u.nickName, tl.taskId, p.`name`, pv.version, tl.log, tl.createTime FROM `task_log` AS tl LEFT JOIN `user` AS u ON tl.uid = u.id LEFT JOIN project_task AS t ON tl.taskId = t.id LEFT JOIN project AS p ON t.pid = p.id LEFT JOIN project_version AS pv ON pv.id =t.vid ${ew.customSqlSegment}")
    Page<TaskLogAggregationListBo> getTaskLogAggregation(Page<TaskLogAggregationListBo> taskLogAggregationListBoPage, @Param(Constants.WRAPPER) Wrapper ew);


    @Select("SELECT * FROM `task_log` WHERE taskId = #{taskId} ORDER BY createTime DESC LIMIT 1;")
    TaskLogPo getLastTaskLog(Integer taskId);
}
