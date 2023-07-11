package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.pojo.bo.StatisticTaskBo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author linyouru
 * @version 1.0
 * @date 2023/7/11 10:16:13
 */
@Repository
public interface StatisticTaskMapper extends BaseMapper<StatisticTaskBo>{

    @Select(" SELECT ta.uid, COUNT(ta.uid) AS taskTotal, SUM(IF(ta.timely = \"2\", 1, 0)) AS taskTimeoutCount, SUM(ta.feedbackCount) AS feedbackCount, SUM(ta.acceptCount) AS acceptCount, SUM(ta.quality) AS qualitySum, SUM(ta.document) AS documentSum, SUM(ta.planWorkTime) AS planWorkTimeSum, SUM(ta.workTimeSum) AS workTimeSum FROM( SELECT t.id,t.uid,t.timely,t.quality,t.document,t.planWorkTime,tl.workTimeSum ,tc.feedbackCount,tc.acceptCount FROM project_task AS t LEFT JOIN (SELECT taskId,SUM(workTime) AS workTimeSum FROM task_log GROUP BY taskId) AS tl ON t.id = tl.taskId LEFT JOIN ( SELECT taskId, SUM(IF(`type`=1,1,0)) AS acceptCount, SUM(IF(`type`=2,1,0)) AS feedbackCount FROM task_check WHERE isTimely=2 GROUP BY taskId) AS tc ON t.id = tc.taskId ${ew.customSqlSegment} ) AS ta GROUP BY ta.uid")
    Page<StatisticTaskBo> statisticTask(Page<StatisticTaskBo> page, @Param(Constants.WRAPPER) Wrapper<StatisticTaskBo> ew);
}
