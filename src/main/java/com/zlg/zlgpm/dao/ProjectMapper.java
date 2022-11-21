package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    @Select("SELECT p.id, p.`name`, p.version, p.uid, u.nickName, p.`status`, p.remark, p.updateTime, p.createTime FROM `project` AS `p` LEFT JOIN `user` AS `u` ON p.uid = u.id ${ew.customSqlSegment}")
    Page<ProjectBo> selectPageByName(Page<ProjectBo> projectBoPage, @Param(Constants.WRAPPER) Wrapper ew);

    @Select("SELECT `name` FROM `project` ${ew.customSqlSegment}")
    List<Map<String, String>> aggregatedProjectName(@Param(Constants.WRAPPER) Wrapper ew);
}
