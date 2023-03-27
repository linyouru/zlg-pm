package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlg.zlgpm.dao.ProjectVersionMapper;
import com.zlg.zlgpm.pojo.po.ProjectVersionPo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectVersionService {

    @Resource
    private ProjectVersionMapper projectVersionMapper;


    public List<ProjectVersionPo> getProjectVersions(Integer pid){
        QueryWrapper<ProjectVersionPo> wrapper = new QueryWrapper<>();
        wrapper.eq("pid",pid);
        return projectVersionMapper.selectList(wrapper);
    }

}
