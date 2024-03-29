package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlg.zlgpm.dao.ProjectModuleMapper;
import com.zlg.zlgpm.dao.TaskMapper;
import com.zlg.zlgpm.pojo.po.ProjectModulePo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectModuleService extends ServiceImpl<ProjectModuleMapper, ProjectModulePo> implements IService<ProjectModulePo> {

    @Resource
    private ProjectModuleMapper projectModuleMapper;
    @Resource
    private TaskMapper taskMapper;


    public void deleteProjectModuleByPid(int pid) {
        QueryWrapper<ProjectModulePo> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", pid);
        int delete = projectModuleMapper.delete(wrapper);
    }

    public void deleteProjectModuleForUpdate(ArrayList<ProjectModulePo> list, Integer pid) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (ProjectModulePo projectModulePo : list) {
            ids.add(projectModulePo.getId());
        }
        QueryWrapper<ProjectModulePo> wrapper = new QueryWrapper<>();
        if (list.size() > 0) {
            wrapper.notIn("id", ids);
        }
        wrapper.eq("pid", pid);
        projectModuleMapper.delete(wrapper);
    }

    public List<ProjectModulePo> queryProjectModule(int pid) {
        QueryWrapper<ProjectModulePo> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", pid);
        wrapper.orderByAsc("level");
        return projectModuleMapper.selectList(wrapper);
    }

    public boolean queryProjectModuleHaveTask(int id) {
        QueryWrapper<TaskPo> wrapper = new QueryWrapper<>();
        wrapper.eq("mid", id);
        Long count = taskMapper.selectCount(wrapper);
        return count > 0;
    }

}
