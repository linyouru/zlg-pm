package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.commom.OperationLog;
import com.zlg.zlgpm.commom.Utils;
import com.zlg.zlgpm.controller.model.ApiCreateTaskRequest;
import com.zlg.zlgpm.controller.model.ApiUpdateTaskRequest;
import com.zlg.zlgpm.dao.*;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.helper.EmailHelper;
import com.zlg.zlgpm.pojo.bo.ProjectVersionBo;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.bo.TaskStatisticsBo;
import com.zlg.zlgpm.pojo.po.*;
import io.swagger.models.auth.In;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private ProjectVersionMapper projectVersionMapper;
    @Resource
    private TaskChangeMapper taskChangeMapper;

    @Resource
    private EmailHelper emailHelper;

    private static final String TASK_CHECK = "3";
    private static final String TASK_END = "1";
    private static final String TASK_TIMEOUT = "2";
    private static final String[] CANNOTCHANGETASKTIME = {"1", "2", "3", "5"};

    @OperationLog(value = "创建任务", type = "Task")
    public TaskPo createTask(Boolean sandEmail, ApiCreateTaskRequest body) {
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
        //设置任务序号
        if (null == task.getSerialNumber()) {
            QueryWrapper<Integer> wrapper = new QueryWrapper<>();
            wrapper.eq("pid", task.getPid());
            wrapper.eq("vid", task.getVid());
            int maxSerialNumber = taskMapper.getMaxSerialNumber(wrapper) != null ? taskMapper.getMaxSerialNumber(wrapper) : 0;
            task.setSerialNumber(maxSerialNumber + 1);
        }
        taskMapper.insert(task);
        if (sandEmail) {
            ProjectVersionPo projectVersionPo = projectVersionMapper.selectById(task.getVid());
            UserPo taskUser = userMapper.selectById(task.getUid());
            String text = emailHelper.assembleEmailMessage(projectPo.getName(), projectVersionPo.getVersion(), task.getTask());
            SimpleMailMessage message = emailHelper.getSimpleMailMessage(EmailHelper.EMAIL_FORM, taskUser.getEmail(), "[项目管理系统]任务指派通知", text);
            emailHelper.sendSimpleMailMessage(message);
        }
        return task;
    }

    @OperationLog(value = "删除任务", type = "Task")
    public TaskPo deleteTask(Integer id) {
        UserPo currentUser = (UserPo) SecurityUtils.getSubject().getPrincipal();
        TaskPo taskPo = taskMapper.selectById(id);
        if (null == taskPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12001", id);
        }
        ProjectPo projectPo = projectMapper.selectById(taskPo.getPid());
        if (Integer.parseInt(currentUser.getId() + "") != taskPo.getUid() && Integer.parseInt(currentUser.getId() + "") != projectPo.getUid()) {
            throw new BizException(HttpStatus.FORBIDDEN, "auth.11001");
        }
        taskMapper.deleteById(id);
        return taskPo;
    }

    @OperationLog(value = "修改任务", type = "Task")
    public TaskPo updateTask(Integer id, ApiUpdateTaskRequest body) {
        TaskPo task = dataConvertHelper.convert2TaskPo(body);
        UserPo currentUser = (UserPo) SecurityUtils.getSubject().getPrincipal();
        TaskPo beforeTask = taskMapper.selectById(id);
        ProjectPo beforeProject = projectMapper.selectById(task.getPid());
        //只有项目负责人和开发人能修改任务
        if (Integer.parseInt(currentUser.getId() + "") != beforeTask.getUid() && Integer.parseInt(currentUser.getId() + "") != beforeProject.getUid()) {
            throw new BizException(HttpStatus.FORBIDDEN, "auth.11001");
        }

        QueryWrapper<TaskChangePo> wrapper = new QueryWrapper<>();
        wrapper.eq("taskId", beforeTask.getId());
        wrapper.eq("status", 1);
        Long taskChangeCount = taskChangeMapper.selectCount(wrapper);
        //任务状态为暂停、待验收、问题反馈、已完成或任务存在待审核变更时不能修改任务时间
        if (Arrays.asList(CANNOTCHANGETASKTIME).contains(beforeTask.getStatus()) || taskChangeCount > 0) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12004");
        }

        Integer uid = task.getUid();
        if (null != uid) {
            UserPo userPo = userMapper.selectById(uid);
            if (null == userPo) {
                throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
            }
        }
        task.setId(id);
        int i = taskMapper.updateById(task);
        if (i == 0) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12001", id);
        }
        TaskPo retTask = taskMapper.selectById(id);

        ProjectPo projectPo = projectMapper.selectById(retTask.getPid());
        ProjectVersionPo projectVersionPo = projectVersionMapper.selectById(retTask.getVid());
        UserPo projectUser = userMapper.selectById(projectPo.getUid());
        UserPo taskUser = userMapper.selectById(retTask.getUid());
        String text = emailHelper.assembleEmailMessage(projectPo.getName(), projectVersionPo.getVersion(), projectUser.getNickName(), taskUser.getNickName(), retTask.getTask());
        if (TASK_CHECK.equals(task.getStatus())) {
            //任务状态改为[待验收]需要给项目负责人发邮件
            SimpleMailMessage message = emailHelper.getSimpleMailMessage(EmailHelper.EMAIL_FORM, projectUser.getEmail(), "[项目管理系统]任务申请验收", text);
            emailHelper.sendSimpleMailMessage(message);
        }

        return retTask;
    }

    public TaskListBo queryTask(Integer id) {
        QueryWrapper<TaskListBo> wrapper = new QueryWrapper<>();
        wrapper.eq("t.id", id);
        TaskListBo taskListBo = taskMapper.queryTask(wrapper);
        ArrayList<UserPo> allUserInfo = userMapper.getAllUserInfo();
        if (taskListBo != null) {
            fillNickName(taskListBo, allUserInfo);
        }
        return taskListBo;
    }

    public Page<TaskListBo> taskList(Integer currentPage, Integer pageSize, Integer projectUid, String status, Integer pid, Integer vid,
                                     Integer uid, String startTime, String endTime, String abnormal, String sortField, Boolean isAsc, String mid,
                                     String level, String task, String detail) {
        QueryWrapper<TaskListBo> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(status)) {
            String[] split = status.split(",");
            queryWrapper.in("t.status", split);
        }
        if (null != pid) {
            queryWrapper.eq("t.pid", pid);
        }
        if (null != vid) {
            queryWrapper.eq("t.vid", vid);
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
            if (TASK_TIMEOUT.equals(abnormal)) {
                //已过期
                queryWrapper.apply("(UNIX_TIMESTAMP() * 1000 - t.playEndTime > 0 ) AND( t.`status`NOT IN (\"1\",\"5\",\"6\"))");
            } else {
                //将过期(48小时内)
                queryWrapper.apply("(t.playEndTime - UNIX_TIMESTAMP() * 1000 BETWEEN 0 AND 172800000) AND( t.`status`NOT IN (\"1\",\"5\",\"6\"))");
            }
        }
        if (null != mid) {
            String[] split = mid.split(",");
            queryWrapper.in("pm.id", split);
        }
        if (StringUtils.hasText(sortField)) {
            String[] split = sortField.split(",");
            List<String> sortList = Arrays.asList(split);
            queryWrapper.orderBy(true, isAsc, sortList);
        }
        if (StringUtils.hasText(level)) {
            String[] split = level.split(",");
            queryWrapper.in("t.level", split);
        }
        if (StringUtils.hasText(task)) {
            queryWrapper.like("t.task", task);
        }
        if (StringUtils.hasText(detail)) {
            queryWrapper.like("t.detail", detail);
        }

        Page<TaskListBo> taskListBoPage = new Page<>();
        taskListBoPage.setCurrent(currentPage);
        taskListBoPage.setSize(pageSize);
        Page<TaskListBo> taskListBo = taskMapper.selectPage(taskListBoPage, queryWrapper);
        return this.taskListFillNickName(taskListBo);
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
     * 修改被拖拽排序任务的序号
     *
     * @param taskIds         任务ids
     * @param newSerialNumber 新序号
     */
    @Transactional(rollbackFor = Exception.class)
    public void handleTaskSort(String[] taskIds, Integer newSerialNumber) {
        if (taskIds.length > 0) {
            //按功能模块拖拽
            Integer firstTaskId = Integer.parseInt(taskIds[0]);
            TaskPo taskPo = taskMapper.selectById(firstTaskId);
            Integer firstTaskOldSerialNumber = taskPo.getSerialNumber();
            ArrayList<String> taskIdList = new ArrayList<>();
            Collections.addAll(taskIdList, taskIds);
            if (firstTaskOldSerialNumber > newSerialNumber) {
                //前移,先移动序号大的再移动序号小的
                Collections.reverse(taskIdList);
            }
            for (String taskId : taskIdList) {
                TaskPo task = taskMapper.selectById(taskId);
                Integer oldSerialNumber = task.getSerialNumber();
                task.setSerialNumber(newSerialNumber);
                taskMapper.updateById(task);
                this.handleSort(task.getPid(), task.getVid(), newSerialNumber, oldSerialNumber, Integer.parseInt(taskId));
            }
        }
    }

    /**
     * 修改受拖拽排序影响的任务的序号
     *
     * @param pid             项目id
     * @param vid             版本id
     * @param newSerialNumber 新序号值
     * @param oldSerialNumber 旧序号值
     * @param taskId          移动的任务id
     */
    public void handleSort(Integer pid, Integer vid, Integer newSerialNumber, Integer oldSerialNumber, Integer taskId) {
        QueryWrapper<Integer> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", pid);
        wrapper.eq("vid", vid);
        wrapper.ne("id", taskId);
        if (null == oldSerialNumber) {
            //插入任务
            wrapper.ge("serialNumber", newSerialNumber);
            taskMapper.frontedTask(wrapper);
            return;
        }
        if (newSerialNumber < oldSerialNumber) {
            //前移
            wrapper.ge("serialNumber", newSerialNumber);
            wrapper.le("serialNumber", oldSerialNumber);
            taskMapper.frontedTask(wrapper);
        } else {
            //后移
            wrapper.le("serialNumber", newSerialNumber);
            wrapper.ge("serialNumber", oldSerialNumber);
            taskMapper.retrusiveTask(wrapper);
        }

    }

    public List<ProjectVersionBo> getTaskRelevance(Integer tid) {
        QueryWrapper<String> wrapper = new QueryWrapper<>();
        wrapper.eq("r.tid", tid);
        return taskMapper.getTaskRelevance(wrapper);
    }

    /**
     * 填充各种人的昵称
     *
     * @param taskListBo
     * @return
     */
    public Page<TaskListBo> taskListFillNickName(Page<TaskListBo> taskListBo) {
        ArrayList<UserPo> allUserInfo = userMapper.getAllUserInfo();
        for (TaskListBo task : taskListBo.getRecords()) {
            if (task != null) {
                fillNickName(task, allUserInfo);
            }
        }
        return taskListBo;
    }

    @OperationLog(value = "验收任务", type = "TaskAccept")
    public TaskPo taskAccept(TaskPo taskPo) {
        //任务状态修改为已完成时,判断任务及时性
        TaskPo beforeTask = taskMapper.selectById(taskPo.getId());
        if (TASK_END.equals(taskPo.getStatus())) {
            String playEndTime = beforeTask.getPlayEndTime();
            long now = System.currentTimeMillis();
            taskPo.setTimely(now < Long.parseLong(playEndTime) ? "1" : "2");
            taskPo.setAcceptanceTime(now + "");
        }
        taskMapper.updateById(taskPo);

        ProjectPo projectPo = projectMapper.selectById(beforeTask.getPid());
        ProjectVersionPo projectVersionPo = projectVersionMapper.selectById(beforeTask.getVid());
        UserPo projectUser = userMapper.selectById(projectPo.getUid());
        UserPo taskUser = userMapper.selectById(beforeTask.getUid());
        String text = emailHelper.assembleEmailMessage(projectPo.getName(), projectVersionPo.getVersion(), projectUser.getNickName(), taskUser.getNickName(), beforeTask.getTask());
        if (TASK_END.equals(taskPo.getStatus())) {
            //任务状态改为[已完成]需要给任务负责人发邮件
            SimpleMailMessage message = emailHelper.getSimpleMailMessage(EmailHelper.EMAIL_FORM, taskUser.getEmail(), "[项目管理系统]任务验收通过", text);
            emailHelper.sendSimpleMailMessage(message);
        }
        return beforeTask;
    }

    private void fillNickName(TaskListBo task, ArrayList<UserPo> allUserInfo) {
        for (UserPo user : allUserInfo) {
            if (task.getUid() != null && Long.parseLong(task.getUid() + "") == user.getId()) {
                task.setNickName(user.getNickName());
            }
            if (task.getAccepterUid() != null && Long.parseLong(task.getAccepterUid() + "") == user.getId()) {
                task.setAccepterNickName(user.getNickName());
            }
            if (task.getCreatedUid() != null && Long.parseLong(task.getCreatedUid() + "") == user.getId()) {
                task.setCreatedUserNickname(user.getNickName());
            }
        }
    }

}
