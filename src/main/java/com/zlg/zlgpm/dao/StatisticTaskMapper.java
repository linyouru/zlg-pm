package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.pojo.bo.StatisticLogBo;
import com.zlg.zlgpm.pojo.bo.StatisticTaskBo;
import com.zlg.zlgpm.pojo.bo.StatisticWorkTimeBo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author linyouru
 * @version 1.0
 * @date 2023/7/11 10:16:13
 */
@Repository
public interface StatisticTaskMapper extends BaseMapper<StatisticTaskBo> {

    Page<StatisticTaskBo> statisticTask(Page<StatisticTaskBo> page, @Param(Constants.WRAPPER) Wrapper<StatisticTaskBo> ew, List<String> sortList, Boolean isAsc);

    @Select("SELECT uid, COUNT(1) AS trueTotal, SUM(IF(workTime = \"0\", 1, 0)) AS abnormal FROM `task_log` ${ew.customSqlSegment} GROUP BY uid")
    Page<StatisticLogBo> statisticLog(Page<StatisticLogBo> page, @Param(Constants.WRAPPER) Wrapper<StatisticLogBo> ew);

    @Select("SELECT uid, SUBSTRING_INDEX(createTime, \" \", 1) AS `day` FROM task_log  ${ew.customSqlSegment} GROUP BY uid, `day`")
    ArrayList<HashMap<String,Object>> getTheoryLogDays(@Param(Constants.WRAPPER) Wrapper<StatisticLogBo> ew);

    Integer statisticPlanWorkTime(@Param(Constants.WRAPPER) Wrapper<StatisticWorkTimeBo> ew, Integer pid, Integer vid);

    @Select("SELECT SUM(tlog.workTime) AS workTime FROM `project_task` AS pt LEFT JOIN( SELECT taskId, SUM(workTime) AS workTime FROM task_log GROUP BY taskId) AS tlog ON pt.id = tlog.taskId ${ew.customSqlSegment};")
    Integer statisticWorkTime(@Param(Constants.WRAPPER) Wrapper<StatisticWorkTimeBo> ew);

    @Select("SELECT t.mid,SUM(t.workTime) AS workTime FROM( SELECT pt.pid,pt.vid,pt.mid,pt.uid,tlog.workTime FROM `project_task` AS pt LEFT JOIN (SELECT taskId,SUM(workTime) AS workTime FROM task_log GROUP BY taskId) AS tlog ON pt.id = tlog.taskId ${ew.customSqlSegment}) AS t GROUP BY t.mid")
    ArrayList<StatisticWorkTimeBo> statisticWorkTimeDetail(@Param(Constants.WRAPPER) Wrapper<StatisticWorkTimeBo> ew);
}
