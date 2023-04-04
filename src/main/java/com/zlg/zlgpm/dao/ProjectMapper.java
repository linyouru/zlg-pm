package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.pojo.bo.ProjectStatisticsBo;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.pojo.bo.ProjectBo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectMapper extends BaseMapper<ProjectPo> {



//    @Select("SELECT p.id, p.`name`, p.version, u.nickName, p.`status`, p.remark, p.createTime FROM `project` AS `p` LEFT JOIN `user` AS `u` ON p.uid = u.id ${ew.customSqlSegment}")
//    List<ProjectBo> selectByName(ProjectPo projectPo, @Param(Constants.WRAPPER) Wrapper ew);

    /**
     * mybatis-plus 使用 Wrapper 自定义SQL,查询项目管理列表数据
     */
    @Select("SELECT p.id, p.`name`, p.uid, u.nickName, p.`status`, p.remark, p.updateTime, p.createTime FROM `project` AS `p` LEFT JOIN `user` AS `u` ON p.uid = u.id ${ew.customSqlSegment}")
    Page<ProjectBo> selectPageByName(Page<ProjectBo> projectBoPage, @Param(Constants.WRAPPER) Wrapper<ProjectBo> ew);

    @Select("SELECT `name` FROM `project` ${ew.customSqlSegment}")
    List<Map<String, String>> aggregatedProjectName(@Param(Constants.WRAPPER) Wrapper<List<Map<String, String>>> ew);

    /**
     * 活动中的项目任务情况统计
     */
    @Select("SELECT t1.id, t1.`name`, t1.version, SUM( IF( ISNULL(t1.`status`), 0, t1.numb) ) AS taskTotal, SUM( IF (t1.`status` = 1, t1.numb, 0) ) AS finishTaskNum FROM ( SELECT p.id, p.`name`, pv.version, t.`status`, count(*) AS numb FROM `project` AS p LEFT JOIN project_task AS t ON p.id = t.pid LEFT JOIN project_version AS pv ON pv.id = t.vid WHERE p.`status` = 1 GROUP BY p.id, p.`name`, pv.id, t.`status` ) AS t1 GROUP BY t1.id, t1.`name`, t1.version ORDER BY t1.`name`, t1.version")
    Page<ProjectStatisticsBo> selectProjectStatistics(Page<ProjectStatisticsBo> projectStatisticsBo);

    @Select("SELECT p.id, p.`name`, p.uid, u.nickName, p.`status`, p.remark, p.updateTime, p.createTime FROM `project` AS `p` LEFT JOIN `user` AS `u` ON p.uid = u.id ${ew.customSqlSegment}")
    ProjectBo selectProjectById(@Param(Constants.WRAPPER) Wrapper<ProjectBo> ew);
}
