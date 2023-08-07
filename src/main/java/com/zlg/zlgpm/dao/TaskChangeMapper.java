package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.pojo.bo.StatisticTaskChangeBo;
import com.zlg.zlgpm.pojo.bo.TaskChangeListBo;
import com.zlg.zlgpm.pojo.po.TaskChangePo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskChangeMapper extends BaseMapper<TaskChangePo> {

    @Select("SELECT t.id, t.taskId, p.`name` AS projectName, pv.version AS projectVersion, pt.task, t.uid, u.userName, u.nickName, t.auditorId, t.auditorName, t.`status`, t.beforeStartTime, t.beforeEndTime, t.afterStartTime, t.afterEndTime, t.reason, t.createTime, t.delayDayCount FROM `task_change` AS t LEFT JOIN `user` AS u ON t.uid = u.id LEFT JOIN project_task AS pt ON t.taskId = pt.id LEFT JOIN project AS p ON pt.pid = p.id LEFT JOIN project_version AS pv ON pv.id = pt.vid ${ew.customSqlSegment}")
    Page<TaskChangeListBo> selectPage(Page<TaskChangeListBo> taskChangeListBoPage, @Param(Constants.WRAPPER) Wrapper ew);

    @Select("SELECT pt.pid,p.`name` AS `project`,pt.vid,pv.version,pt.id AS tid,pt.task,tc.beforeStartTime,tc.beforeEndTime,tc.afterStartTime,tc.afterEndTime,tc.reason,tc.uid,u.nickName,tc.auditorName,tc.createTime, tc.delayDayCount  FROM `task_change` AS tc LEFT JOIN project_task AS pt ON tc.taskId = pt.id LEFT JOIN project AS p ON p.id = pt.pid LEFT JOIN project_version AS pv ON pv.id = pt.vid LEFT JOIN `user` AS u ON u.id = tc.uid ${ew.customSqlSegment} ")
    Page<StatisticTaskChangeBo> getStatisticTaskChangeList(Page<StatisticTaskChangeBo> page, @Param(Constants.WRAPPER) Wrapper<StatisticTaskChangeBo> ew);
}
