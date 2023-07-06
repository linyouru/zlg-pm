package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlg.zlgpm.pojo.bo.ProjectVersionBo;
import com.zlg.zlgpm.pojo.po.ProjectVersionPo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectVersionMapper extends BaseMapper<ProjectVersionPo> {

    @Select("SELECT pv.id, pv.version, pv.pid, p.`name` FROM project_version AS pv LEFT JOIN `project` AS p ON pv.pid = p.id ORDER BY pv.pid;")
    List<ProjectVersionBo> getProjectVersionsAll();


}
