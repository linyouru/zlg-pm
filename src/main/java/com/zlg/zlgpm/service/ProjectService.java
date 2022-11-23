package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.commom.OperationLog;
import com.zlg.zlgpm.controller.model.ApiCreateProjectRequest;
import com.zlg.zlgpm.dao.ProjectMapper;
import com.zlg.zlgpm.dao.TaskMapper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.pojo.bo.ProjectBo;
import com.zlg.zlgpm.pojo.bo.ProjectStatisticsBo;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.helper.DataConvertHelper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {

    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private DataConvertHelper dataConvertHelper;

    @OperationLog(value = "创建项目",type ="Project" )
    public ProjectPo createProject(ApiCreateProjectRequest request) {
        UserPo userPo = userMapper.selectById(request.getUid());
        if (null == userPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
        }
        ProjectPo projectPo = dataConvertHelper.convert2ProjectPo(request);
        try {
            int insert = projectMapper.insert(projectPo);
        } catch (DuplicateKeyException e) {
            throw new BizException(HttpStatus.BAD_REQUEST, "project.11001");
        }
        return projectPo;
    }

    @OperationLog(value = "删除项目",type ="Project" )
    public ProjectPo deleteProject(Integer id) {
        Long taskCount = taskMapper.selectCount(new QueryWrapper<TaskPo>().eq("pid", id));
        if(taskCount>0){
            //删除项目前要先删除项目下挂载的任务
            throw new BizException(HttpStatus.BAD_REQUEST,"project.11003",id);
        }
        ProjectPo projectPo = projectMapper.selectById(id);
        if (null == projectPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "project.11002", id);
        }
        projectMapper.deleteById(id);
        return projectPo;
    }

    @OperationLog(value = "修改项目",type ="Project" )
    public ProjectPo updateProject(ProjectPo projectPo) {
        try {
            int i = projectMapper.updateById(projectPo);
            if (i == 0) {
                throw new BizException(HttpStatus.BAD_REQUEST, "project.11002", projectPo.getId());
            }
        } catch (DuplicateKeyException e) {
            throw new BizException(HttpStatus.BAD_REQUEST, "project.11001");
        }
        return projectMapper.selectById(projectPo.getId());
    }

    public Page<ProjectBo> projectList(String name, Integer currentPage, Integer pageSize) {
        QueryWrapper<ProjectBo> queryWrapper = new QueryWrapper<>();
        if (null != name) {
            queryWrapper.eq("name", name);
        }
        Page<ProjectBo> projectBoPage = new Page<>();
        projectBoPage.setCurrent(currentPage);
        projectBoPage.setSize(pageSize);
        return projectMapper.selectPageByName(projectBoPage, queryWrapper);
    }

    public List<Map<String, String>> aggregatedProjectName(){
        QueryWrapper<List<Map<String, String>>> queryWrapper = new QueryWrapper<>();
        queryWrapper.groupBy("name");
        return projectMapper.aggregatedProjectName(queryWrapper);
    }

    public Page<ProjectStatisticsBo> selectProjectStatistics(Integer currentPage, Integer pageSize){
        Page<ProjectStatisticsBo> projectStatisticsBo = new Page<>();
        projectStatisticsBo.setCurrent(currentPage);
        projectStatisticsBo.setSize(pageSize);
        return projectMapper.selectProjectStatistics(projectStatisticsBo);
    }
}
