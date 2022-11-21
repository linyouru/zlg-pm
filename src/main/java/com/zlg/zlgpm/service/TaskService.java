package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.commom.OperationLog;
import com.zlg.zlgpm.controller.model.ApiCreateTaskRequest;
import com.zlg.zlgpm.controller.model.ApiUpdateTaskRequest;
import com.zlg.zlgpm.dao.ProjectMapper;
import com.zlg.zlgpm.dao.TaskMapper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.bo.ProjectBo;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskService {

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private DataConvertHelper dataConvertHelper;

    @OperationLog(value = "创建任务", type = "Task")
    public TaskPo createTask(ApiCreateTaskRequest body) {
        TaskPo task = dataConvertHelper.convert2TaskPo(body);
        UserPo userPo = userMapper.selectById(task.getUid());
        ProjectPo projectPo = projectMapper.selectById(task.getPid());
        if (null == userPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
        }
        if (null == projectPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "project.11002", task.getPid());
        }
        int insert = taskMapper.insert(task);
        return task;
    }

    @OperationLog(value = "删除任务", type = "Task")
    public TaskPo deleteTask(Integer id) {
        TaskPo taskPo = taskMapper.selectById(id);
        if (null == taskPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12001", id);
        }
        taskMapper.deleteById(id);
        return taskPo;
    }

    @OperationLog(value = "修改任务", type = "Task")
    public TaskPo updateTask(Integer id, ApiUpdateTaskRequest body) {
        TaskPo task = dataConvertHelper.convert2TaskPo(body);
        task.setId(id);
        Integer uid = task.getUid();
        if (null != uid) {
            UserPo userPo = userMapper.selectById(uid);
            if (null == userPo) {
                throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
            }
        }
        //##################待补充功能##################
        //任务状态改为[已完成]需要个给项目负责人发邮件
        int i = taskMapper.updateById(task);
        if (i == 0) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12001", id);
        }
        return task;
    }

    public Page<TaskListBo> taskList(Integer currentPage, Integer pageSize, String status, String projectName, String projectVersion,
                                     Integer uid, String startTime, String endTime, String abnormal) {
        QueryWrapper<TaskListBo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(status)) {
            queryWrapper.eq("t.status", status);
        }
        if (StringUtils.hasText(projectName)) {
            queryWrapper.likeRight("p.name", projectName);
        }
        if (StringUtils.hasText(projectVersion)) {
            queryWrapper.likeRight("p.version", projectVersion);
        }
        if (null != uid) {
            queryWrapper.eq("t.uid", uid);
        }
        if (StringUtils.hasText(startTime) && StringUtils.hasText(endTime)) {
            queryWrapper.ge("t.playStartTime", Long.parseLong(startTime));
            queryWrapper.le("t.playStartTime", Long.parseLong(endTime));
        }
        if (StringUtils.hasText(abnormal)) {
            if ("2".equals(abnormal)) {
                //已过期
                queryWrapper.apply("UNIX_TIMESTAMP() * 1000 - t.playEndTime > 0");
            } else {
                //将过期(48小时内)
                queryWrapper.apply("t.playEndTime - UNIX_TIMESTAMP() * 1000 BETWEEN 0 AND 86400000");
            }
        }

        Page<TaskListBo> taskListBoPage = new Page<>();
        taskListBoPage.setCurrent(currentPage);
        taskListBoPage.setSize(pageSize);
        return taskMapper.selectPage(taskListBoPage, queryWrapper);
    }


}
