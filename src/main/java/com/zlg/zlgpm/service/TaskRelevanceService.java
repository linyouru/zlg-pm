package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlg.zlgpm.dao.TaskRelevanceMapper;
import com.zlg.zlgpm.pojo.po.TaskRelevancePo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TaskRelevanceService extends ServiceImpl<TaskRelevanceMapper, TaskRelevancePo> implements IService<TaskRelevancePo> {

    @Resource
    private TaskRelevanceMapper taskRelevanceMapper;

    public void deleteTaskRelevanceByTid(Integer tid){
        QueryWrapper<TaskRelevancePo> wrapper = new QueryWrapper<>();
        wrapper.eq("tid",tid);
        taskRelevanceMapper.delete(wrapper);
    }

}
