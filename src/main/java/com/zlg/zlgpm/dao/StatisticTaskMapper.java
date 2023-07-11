package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.pojo.bo.StatisticLogBo;
import com.zlg.zlgpm.pojo.bo.StatisticTaskBo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author linyouru
 * @version 1.0
 * @date 2023/7/11 10:16:13
 */
@Repository
public interface StatisticTaskMapper extends BaseMapper<StatisticTaskBo> {

    Page<StatisticTaskBo> statisticTask(Page<StatisticTaskBo> page, @Param(Constants.WRAPPER) Wrapper<StatisticTaskBo> ew, List<String> sortList, Boolean isAsc);

//    @Select("SELECT uid, COUNT(1) AS logTotal, SUM(IF(workTime = \"0\",1,0)) AS abnormal FROM `task_log` ${ew.customSqlSegment} GROUP BY uid")
    @Select("SELECT a.uid, a.trueTotal, a.abnormal, b.theoryTotal FROM( SELECT uid, COUNT(1) AS trueTotal, SUM(IF(workTime = \"0\", 1, 0)) AS abnormal FROM `task_log` ${ew.customSqlSegment} GROUP BY uid) AS a LEFT JOIN ( SELECT t.uid, COUNT(1) AS theoryTotal FROM ( SELECT uid, SUBSTRING_INDEX(createTime, \" \", 1) AS `day` FROM task_log ${ew.customSqlSegment} GROUP BY uid, `day` ) AS t GROUP BY t.uid ) AS b ON a.uid = b.uid")
    Page<StatisticLogBo> statisticLog(Page<StatisticLogBo> page, @Param(Constants.WRAPPER) Wrapper<StatisticLogBo> ew);

}
