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

import java.util.ArrayList;

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
            "        t.feedback,\n" +
            "        t.createTime\n" +
            "FROM `task_log` AS t\n" +
            "LEFT JOIN `user` AS u\n" +
            "    ON t.uid = u.id\n" +
            "${ew.customSqlSegment}")
    Page<TaskLogListBo> selectPage(Page<TaskLogListBo> taskLogListBoPage, @Param(Constants.WRAPPER) Wrapper<TaskLogListBo> ew);

    @Select("SELECT tl.id,\n" +
            "         tl.uid,\n" +
            "         u.userName,\n" +
            "         u.nickName,\n" +
            "         tl.taskId,\n" +
            "         p.`name`,\n" +
            "         t.task,\n" +
            "         pv.version,\n" +
            "         tl.log,\n" +
            "         tl.feedback,\n" +
            "         tl.workTime,\n" +
            "         tl.progress,\n" +
            "         tl.createTime\n" +
            "FROM `task_log` AS tl\n" +
            "LEFT JOIN `user` AS u\n" +
            "    ON tl.uid = u.id\n" +
            "LEFT JOIN project_task AS t\n" +
            "    ON tl.taskId = t.id\n" +
            "LEFT JOIN project AS p\n" +
            "    ON t.pid = p.id\n" +
            "LEFT JOIN project_version AS pv ON t.vid = pv.id\n" +
            "${ew.customSqlSegment}")
    Page<TaskLogAggregationListBo> getTaskLogAggregation(Page<TaskLogAggregationListBo> taskLogAggregationListBoPage, @Param(Constants.WRAPPER) Wrapper<TaskLogAggregationListBo> ew);


    @Select("SELECT * FROM `task_log` WHERE taskId = #{taskId} ORDER BY createTime DESC LIMIT 1;")
    TaskLogPo getLastTaskLog(Integer taskId);

    /**
     * 获取今天写了任务日志的uid
     */
    @Select("SELECT DISTINCT uid FROM `task_log` ${ew.customSqlSegment}")
    ArrayList<Integer> getTodayTaskLogUid(@Param(Constants.WRAPPER) Wrapper<Integer> ew);

    @Select("SELECT SUM(workTime) AS workTime FROM task_log ${ew.customSqlSegment}")
    Double getTodayWorkTime(@Param(Constants.WRAPPER) Wrapper<Integer> ew);

    @Select("SELECT taskId FROM `task_log` WHERE workTime = \"0\" GROUP BY taskId;")
    ArrayList<Integer> getWorkTimeAbnormalTaskId();

    @Select("SELECT SUBSTRING_INDEX(createTime, \" \", 1) AS `day` FROM task_log ${ew.customSqlSegment} GROUP BY `day`")
    ArrayList<String> getLogMissing(@Param(Constants.WRAPPER) Wrapper<String> ew);

}
