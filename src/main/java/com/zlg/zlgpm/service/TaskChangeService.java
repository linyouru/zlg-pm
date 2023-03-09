package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.ApiCreateTaskChangeRequest;
import com.zlg.zlgpm.dao.TaskChangeMapper;
import com.zlg.zlgpm.dao.TaskMapper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.bo.TaskChangeListBo;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.po.TaskChangePo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TaskChangeService {

    @Resource
    private TaskChangeMapper taskChangeMapper;
    @Resource
    private DataConvertHelper dataConvertHelper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private UserMapper userMapper;

    public void createTaskChange(ApiCreateTaskChangeRequest body) {
        TaskChangePo taskChangePo = dataConvertHelper.convert2TaskChangePo(body);

        if (taskChangePo.getUid() != null) {
            UserPo userPo = userMapper.selectById(taskChangePo.getUid());
            if (null == userPo) {
                throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
            }
        }
        if (taskChangePo.getTaskId() != null) {
            TaskPo taskPo = taskMapper.selectById(taskChangePo.getTaskId());
            if (null == taskPo) {
                throw new BizException(HttpStatus.BAD_REQUEST, "task.12001",taskChangePo.getTaskId());
            }
        }
        taskChangeMapper.insert(taskChangePo);
    }

    public Page<TaskChangeListBo> getTaskChange(Integer currentPage, Integer pageSize,Integer taskId){
        QueryWrapper<TaskChangeListBo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("taskId",taskId);
        queryWrapper.orderByDesc("createTime");

        Page<TaskChangeListBo> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        return taskChangeMapper.selectPage(page, queryWrapper);
    }

}
