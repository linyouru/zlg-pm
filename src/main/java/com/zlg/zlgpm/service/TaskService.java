package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.commom.OperationLog;
import com.zlg.zlgpm.commom.Utils;
import com.zlg.zlgpm.controller.model.ApiCreateTaskRequest;
import com.zlg.zlgpm.controller.model.ApiUpdateTaskRequest;
import com.zlg.zlgpm.dao.ProjectMapper;
import com.zlg.zlgpm.dao.TaskMapper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.helper.EmailHelper;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.bo.TaskStatisticsBo;
import com.zlg.zlgpm.pojo.po.ProjectPo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class TaskService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProjectMapper projectMapper;
    @Resource
    private DataConvertHelper dataConvertHelper;

    @Resource
    private EmailHelper emailHelper;

    private static final String EMAIL_FORM = "noreply_developer@zlg.cn";

    @OperationLog(value = "创建任务", type = "Task")
    public TaskPo createTask(ApiCreateTaskRequest body) {
        UserPo currentUser = (UserPo) SecurityUtils.getSubject().getPrincipal();
        TaskPo task = dataConvertHelper.convert2TaskPo(body);
        //任务负责人暂时改为非必填
        if (task.getUid() != null) {
            UserPo userPo = userMapper.selectById(task.getUid());
            if (null == userPo) {
                throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
            }
        }
        ProjectPo projectPo = projectMapper.selectById(task.getPid());
        if (null == projectPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "project.11002", task.getPid());
        }
        task.setCreatedUid(currentUser.getId().intValue());
        task.setCreatedUserNickname(currentUser.getNickName());
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

        ProjectPo projectPo = projectMapper.selectById(retTask.getPid());
        UserPo projectUser = userMapper.selectById(projectPo.getUid());
        UserPo taskUser = userMapper.selectById(retTask.getUid());
        String text = assembleEmailMessage(projectPo.getName(), projectPo.getVersion(), projectUser.getNickName(), taskUser.getNickName(), retTask.getTask());
        if ("3".equals(task.getStatus())) {
            //任务状态改为[待验收]需要给项目负责人发邮件
            SimpleMailMessage message = emailHelper.getSimpleMailMessage(EMAIL_FORM, projectUser.getEmail(), "[项目管理系统]任务申请验收", text);
            emailHelper.sendSimpleMailMessage(message);
        }
        if ("1".equals(task.getStatus())) {
            //任务状态改为[已完成]需要给任务负责人发邮件
            SimpleMailMessage message = emailHelper.getSimpleMailMessage(EMAIL_FORM, taskUser.getEmail(), "[项目管理系统]任务验收通过", text);
            emailHelper.sendSimpleMailMessage(message);
        }
        return retTask;
    }

    public Page<TaskListBo> taskList(Integer currentPage, Integer pageSize, Integer projectUid, String status, String projectName, String projectVersion,
                                     Integer uid, String startTime, String endTime, String abnormal, String sortField, Boolean isAsc, Integer mid) {
        QueryWrapper<TaskListBo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(status)) {
            String[] split = status.split(",");
            queryWrapper.in("t.status", split);
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
        if (null != projectUid) {
            queryWrapper.eq("p.uid", projectUid);
        }
        if (StringUtils.hasText(startTime) && StringUtils.hasText(endTime)) {
            queryWrapper.ge("t.playStartTime", Long.parseLong(startTime));
            queryWrapper.le("t.playStartTime", Long.parseLong(endTime));
        }
        if (StringUtils.hasText(abnormal)) {
            if ("2".equals(abnormal)) {
                //已过期
                queryWrapper.apply("(UNIX_TIMESTAMP() * 1000 - t.playEndTime > 0 ) AND( t.`status`NOT IN (\"1\",\"5\",\"6\"))");
            } else {
                //将过期(48小时内)
                queryWrapper.apply("(t.playEndTime - UNIX_TIMESTAMP() * 1000 BETWEEN 0 AND 172800000) AND( t.`status`NOT IN (\"1\",\"5\",\"6\"))");
            }
        }
        if (null != mid) {
            queryWrapper.eq("pm.id", mid);
        }
        if (StringUtils.hasText(sortField)) {
            String[] split = sortField.split(",");
            List<String> sortList = Arrays.asList(split);
            queryWrapper.orderBy(true, isAsc, sortList);
        }

        Page<TaskListBo> taskListBoPage = new Page<>();
        taskListBoPage.setCurrent(currentPage);
        taskListBoPage.setSize(pageSize);
        return taskMapper.selectPage(taskListBoPage, queryWrapper);
    }

    public TaskStatisticsBo selectTaskStatistics(Integer pid, Integer uid) {
        QueryWrapper<TaskStatisticsBo> wrapper = new QueryWrapper<>();
        if (null != pid) {
            wrapper.eq("pid", pid);
        }
        if (null != uid) {
            wrapper.eq("uid", uid);
        }
        return taskMapper.selectTaskStatistics(wrapper);
    }

    /**
     * 拼接邮件信息
     *
     * @param projectName
     * @param projectVersion
     * @param projectUserName
     * @param taskUsername
     * @param task
     * @return
     */
    private String assembleEmailMessage(String projectName, String projectVersion, String projectUserName, String taskUsername, String task) {
        return "申请时间：" + Utils.convertTimestamp2Date(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n" +
                "项目名称： " + projectName + "\n" +
                "项目版本号： " + projectVersion + "\n" +
                "项目负责人： " + projectUserName + "\n" +
                "开发负责人： " + taskUsername + "\n" +
                "任务内容： " + task;
    }

}
