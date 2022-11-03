package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.ApiCreateProjectRequest;
import com.zlg.zlgpm.dao.ProjectMapper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.pojo.bo.ProjectBo;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.helper.DataConvertHelper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProjectService {

    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private DataConvertHelper dataConvertHelper;

    public void createProject(ApiCreateProjectRequest request) {
        UserPo userPo = userMapper.selectById(request.getUid());
        if (null == userPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
        }
        ProjectPo projectPo = dataConvertHelper.convert2Project(request);
        try {
            int insert = projectMapper.insert(projectPo);
        } catch (DuplicateKeyException e) {
            throw new BizException(HttpStatus.BAD_REQUEST, "project.11001");
        }
    }

    public void deleteProject(Integer id) {
        //##################待补充功能##################
        //删除项目前要先删除项目下挂载的任务
        int i = projectMapper.deleteById(id);
        if (i == 0) {
            throw new BizException(HttpStatus.BAD_REQUEST, "project.11002", id);
        }
    }

    public void updateProject(ProjectPo projectPo) {
        try {
            int i = projectMapper.updateById(projectPo);
            if (i == 0) {
                throw new BizException(HttpStatus.BAD_REQUEST, "project.11002", projectPo.getId());
            }
        } catch (DuplicateKeyException e) {
            throw new BizException(HttpStatus.BAD_REQUEST, "project.11001");
        }
    }

    public Page<ProjectBo> projectList(String name, Integer currentPage, Integer pageSize) {
        QueryWrapper<ProjectBo> queryWrapper = new QueryWrapper<ProjectBo>();
        if (null != name) {
            queryWrapper.eq("name", name);
        }
        Page<ProjectBo> projectBoPage = new Page<ProjectBo>();
        projectBoPage.setCurrent(currentPage);
        projectBoPage.setSize(pageSize);
        return projectMapper.selectPageByName(projectBoPage, queryWrapper);
    }
}