package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlg.zlgpm.controller.model.ApiCreateProjectVersionRequest;
import com.zlg.zlgpm.dao.ProjectMapper;
import com.zlg.zlgpm.dao.ProjectVersionMapper;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.pojo.bo.ProjectBo;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.pojo.po.ProjectVersionPo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectVersionService {

    @Resource
    private ProjectVersionMapper projectVersionMapper;
    @Resource
    private ProjectMapper projectMapper;

    public List<ProjectVersionPo> getProjectVersions(Integer pid) {
        QueryWrapper<ProjectVersionPo> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", pid);
        return projectVersionMapper.selectList(wrapper);
    }

    public void createProjectVersion(ProjectVersionPo projectVersion) {
        ProjectPo projectPo = projectMapper.selectById(projectVersion.getPid());
        if (null == projectPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "project.11002", projectVersion.getPid());
        }
        int insert = projectVersionMapper.insert(projectVersion);
    }

}
