package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlg.zlgpm.dao.TaskMapper;
import com.zlg.zlgpm.dao.TaskRelevanceMapper;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.po.TaskRelevancePo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskRelevanceService extends ServiceImpl<TaskRelevanceMapper, TaskRelevancePo> implements IService<TaskRelevancePo> {

    @Resource
    private TaskRelevanceMapper taskRelevanceMapper;
    @Resource
    private TaskMapper taskMapper;

    public void deleteTaskRelevanceByTid(Integer tid) {
        QueryWrapper<TaskRelevancePo> wrapper = new QueryWrapper<>();
        wrapper.eq("tid", tid);
        taskRelevanceMapper.delete(wrapper);
    }

    public Page<TaskListBo> getTaskRelevanceList(Integer vid) {
        QueryWrapper<TaskRelevancePo> wrapper = new QueryWrapper<>();
        wrapper.eq("vid", vid);
        List<TaskRelevancePo> taskRelevancePos = taskRelevanceMapper.selectList(wrapper);
        ArrayList<Integer> taskIdList = new ArrayList<>();
        for (TaskRelevancePo taskRelevancePo : taskRelevancePos) {
            taskIdList.add(taskRelevancePo.getTid());
        }
        QueryWrapper<TaskListBo> queryWrapper = new QueryWrapper<>();
        if(taskIdList.size() > 0) {
            queryWrapper.in("t.id", taskIdList);
        }else {
            queryWrapper.eq("t.id",-1);
        }
        Page<TaskListBo> page = new Page<>();
        page.setCurrent(1);
        page.setSize(500);
        return taskMapper.selectPage(page, queryWrapper);
    }

}
