package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.pojo.bo.ProjectVersionBo;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.bo.TaskStatisticsBo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TaskMapper extends BaseMapper<TaskPo> {


    @Select("SELECT t.id,\n" +
            "         p.`name` AS projectName,\n" +
            "         pv.version AS projectVersion,\n" +
            "         p.uid AS projectUid,\n" +
            "         t.taskType,\n" +
            "         t.task,\n" +
            "         t.detail,\n" +
            "         t.level,\n" +
            "         t.accepterId,\n" +
            "         t.pid,\n" +
            "         t.uid,\n" +
            "         t.vid,\n" +
            "         t.`status`,\n" +
            "         u.nickName,\n" +
            "         t.playStartTime,\n" +
            "         t.playEndTime,\n" +
            "         t.timely,\n" +
            "         t.quality,\n" +
            "         t.document,\n" +
            "         t.remark,\n" +
            "         t.link,\n" +
            "         pm.module,\n" +
            "         pm.`level` AS moduleLevel,\n" +
            "         t.haveDocument,\n" +
            "         t.updateTime,\n" +
            "         t.createTime,\n" +
            "         t.acceptanceTime,\n" +
            "         t.createdUid,\n" +
            "         t.createdUserNickname,\n" +
            "         IF(((UNIX_TIMESTAMP() * 1000 - t.playEndTime > 0 ) AND( t.`status`NOT IN (\"1\",\"5\",\"6\"))),1,0 ) AS overtime,\n" +
            "         IF(((t.playEndTime - UNIX_TIMESTAMP() * 1000 BETWEEN 0 AND 172800000) AND( t.`status`NOT IN (\"1\",\"5\",\"6\"))),1,0 ) AS warning,\n"+
            "         task_1.workTimeCount,\n" +
            "         task_1.progress\n"+
            "FROM `project_task` AS t\n" +
            "LEFT JOIN `project` AS p\n" +
            "    ON t.pid = p.id\n" +
            "LEFT JOIN `user` AS u\n" +
            "    ON t.uid = u.id\n" +
            "LEFT JOIN (\n" +
            "\tSELECT tla.taskId,tla.progress,tlb.workTimeCount from (\n" +
            "\t\tSELECT a.taskId,a.progress from task_log AS a WHERE createTime = (SELECT MAX(createTime) FROM task_log AS b WHERE taskId = a.taskId)\n" +
            "\t) as tla LEFT JOIN (\n" +
            "\t\tSELECT\n" +
            "\t\ttaskId,\n" +
            "\t\tSUM(workTime) AS workTimeCount\n" +
            "\t\tFROM\n" +
            "\t\t\t`task_log` \n" +
            "\t\tGROUP BY taskId\n" +
            "\t) as tlb ON tla.taskId = tlb.taskId\n" +
            ") AS task_1 ON t.id = task_1.taskId\n"+
            "LEFT JOIN project_modules AS pm ON t.mid = pm.id\n"+
            "LEFT JOIN project_version AS pv ON t.vid = pv.id\n"+
            "${ew.customSqlSegment}\n")
    Page<TaskListBo> selectPage(Page<TaskListBo> taskListBoPage, @Param(Constants.WRAPPER) Wrapper ew);

    @Select("SELECT FORMAT( COUNT(IF(`status` = 1, 1, NULL)) / COUNT(*), 2) AS rateOfFinish, COUNT(IF(`status` = 1, 1, NULL)) AS finishTaskNum, COUNT(*) AS taskTotal, COUNT(IF(`status` = 4, 1, NULL)) AS progressTaskNum, COUNT( IF( ( playEndTime - UNIX_TIMESTAMP() * 1000 ) BETWEEN 0 AND 172800000 AND `status`!=1, 1, NULL ) ) AS warningTaskNum, COUNT( IF ( UNIX_TIMESTAMP() * 1000 - playEndTime > 0 AND `status`!=1, 1, NULL ) ) AS overtimeTaskNum FROM `project_task` ${ew.customSqlSegment}")
    TaskStatisticsBo selectTaskStatistics(@Param(Constants.WRAPPER) Wrapper<TaskStatisticsBo> ew);

    @Select("SELECT MAX(serialNumber) max FROM project_task ${ew.customSqlSegment}")
    Integer getMaxSerialNumber(@Param(Constants.WRAPPER) Wrapper<Integer> ew);

    @Select("UPDATE `project_task` SET serialNumber = serialNumber+1 ${ew.customSqlSegment}")
    Integer frontedTask(@Param(Constants.WRAPPER) Wrapper<Integer> ew);

    @Select("UPDATE `project_task` SET serialNumber = serialNumber-1 ${ew.customSqlSegment}")
    Integer retrusiveTask(@Param(Constants.WRAPPER) Wrapper<Integer> ew);

    @Select("SELECT v.id, v.version, v.pid, p.`name` FROM task_relevance AS r LEFT JOIN project_version AS v ON r.vid = v.id LEFT JOIN project AS p ON v.pid = p.id ${ew.customSqlSegment}")
    List<ProjectVersionBo>getTaskRelevance(@Param(Constants.WRAPPER) Wrapper<String> ew);
}
