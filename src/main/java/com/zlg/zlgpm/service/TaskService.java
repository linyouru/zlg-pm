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
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.bo.TaskStatisticsBo;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

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
    @Resource
    private JavaMailSender mailSender;

    @OperationLog(value = "创建任务", type = "Task")
    public TaskPo createTask(ApiCreateTaskRequest body) {
        TaskPo task = dataConvertHelper.convert2TaskPo(body);
        //任务负责人暂时改为非必填
//        UserPo userPo = userMapper.selectById(task.getUid());
//        if (null == userPo) {
//            throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
//        }
        ProjectPo projectPo = projectMapper.selectById(task.getPid());
        if (null == projectPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "project.11002", task.getPid());
        }
        taskMapper.insert(task);
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
        int i = taskMapper.updateById(task);
        if (i == 0) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12001", id);
        }
        TaskPo retTask = taskMapper.selectById(id);
        if ("2".equals(task.getStatus())) {
            //任务状态改为[待验收]需要给项目负责人发邮件
            ProjectPo projectPo = projectMapper.selectById(retTask.getPid());
            UserPo userPo = userMapper.selectById(projectPo.getUid());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply_developer@zlg.cn");
            message.setTo(userPo.getEmail());
            message.setSubject("[项目管理系统]任务更新");
            message.setText("项目[" + projectPo.getName() + " " + projectPo.getVersion() + "]的任务[" + retTask.getTask() + "]已更新,请查看.");
            message.setSentDate(new Date());
            mailSender.send(message);
        }
        return retTask;
    }

    public Page<TaskListBo> taskList(Integer currentPage, Integer pageSize, String status, String projectName, String projectVersion,
                                     Integer uid, String startTime, String endTime, String abnormal, String sortField, Boolean isAsc) {
        QueryWrapper<TaskListBo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(status)) {
            queryWrapper.eq("t.status", status);
        }
        if (StringUtils.hasText(projectName)) {
            queryWrapper.eq("p.name", projectName);
        }
        if (StringUtils.hasText(projectVersion)) {
            queryWrapper.eq("p.version", projectVersion);
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
                queryWrapper.apply("(UNIX_TIMESTAMP() * 1000 - t.playEndTime > 0 ) AND( t.`status`!= \"3\")");
            } else {
                //将过期(48小时内)
                queryWrapper.apply("(t.playEndTime - UNIX_TIMESTAMP() * 1000 BETWEEN 0 AND 172800000) AND( t.`status`!= \"3\")");
            }
        }
        if (StringUtils.hasText(sortField)) {
            queryWrapper.orderBy(true, isAsc, sortField);
        }

        Page<TaskListBo> taskListBoPage = new Page<>();
        taskListBoPage.setCurrent(currentPage);
        taskListBoPage.setSize(pageSize);
        return taskMapper.selectPage(taskListBoPage, queryWrapper);
    }

    public TaskStatisticsBo selectTaskStatistics() {
        return taskMapper.selectTaskStatistics();
    }

}
