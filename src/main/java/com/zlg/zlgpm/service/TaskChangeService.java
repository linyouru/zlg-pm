package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.ApiCreateTaskChangeRequest;
import com.zlg.zlgpm.controller.model.ApiUpdateTaskChangeRequest;
import com.zlg.zlgpm.dao.ProjectMapper;
import com.zlg.zlgpm.dao.TaskChangeMapper;
import com.zlg.zlgpm.dao.TaskMapper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.bo.TaskChangeListBo;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.pojo.po.TaskChangePo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Resource
    private ProjectMapper projectMapper;

    public void createTaskChange(ApiCreateTaskChangeRequest body) {
        //任务存在待审核的变更记录则不能再创建
        QueryWrapper<TaskChangePo> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        wrapper.eq("taskId",body.getTaskId());
        Long count = taskChangeMapper.selectCount(wrapper);
        if (count > 0) {
            throw new BizException(HttpStatus.BAD_REQUEST, "taskChange.15001", body.getTaskId());
        }

        TaskChangePo taskChangePo = dataConvertHelper.convert2TaskChangePo(body);

        UserPo userPo = userMapper.selectById(taskChangePo.getUid());
        if (null == userPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
        }
        TaskPo taskPo = taskMapper.selectById(taskChangePo.getTaskId());
        if (null == taskPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12001", taskChangePo.getTaskId());
        }
        Integer pid = taskPo.getPid();
        ProjectPo projectPo = projectMapper.selectById(pid);
        int auditorId = projectPo.getUid();
        UserPo auditor = userMapper.selectById(auditorId);
        taskChangePo.setAuditorId(auditorId);
        taskChangePo.setAuditorName(auditor.getNickName());
        taskChangeMapper.insert(taskChangePo);
    }

    public Page<TaskChangeListBo> getTaskChange(Integer taskId, Integer auditorId, String status, Integer currentPage, Integer pageSize, String sortField, Boolean isAsc) {
        QueryWrapper<TaskChangeListBo> queryWrapper = new QueryWrapper<>();
        if (null != taskId) {
            queryWrapper.eq("taskId", taskId);
        }
        if (null != auditorId) {
            queryWrapper.eq("auditorId", auditorId);
        }
        if (StringUtils.hasText(status)) {
            String[] split = status.split(",");
            queryWrapper.in("t.status", split);
        }
        if (null != sortField) {
            queryWrapper.orderBy(true, isAsc, sortField);
        }

        Page<TaskChangeListBo> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        return taskChangeMapper.selectPage(page, queryWrapper);
    }

    public void updateTaskChange(ApiUpdateTaskChangeRequest body, Integer id) {
        TaskChangePo taskChangePo = new TaskChangePo();
        taskChangePo.setStatus(body.getStatus());
        taskChangePo.setId(id);
        taskChangeMapper.updateById(taskChangePo);
    }

}
