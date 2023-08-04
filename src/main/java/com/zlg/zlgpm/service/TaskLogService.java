package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.commom.Utils;
import com.zlg.zlgpm.commom.WorkDayUtils;
import com.zlg.zlgpm.controller.model.ApiCreateTaskLogRequest;
import com.zlg.zlgpm.controller.model.ApiUpdateTaskLogRequest;
import com.zlg.zlgpm.dao.TaskLogMapper;
import com.zlg.zlgpm.dao.TaskMapper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.bo.TaskLogAggregationListBo;
import com.zlg.zlgpm.pojo.bo.TaskLogListBo;
import com.zlg.zlgpm.pojo.po.TaskLogPo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TaskLogService {

    @Resource
    private TaskLogMapper taskLogMapper;
    @Resource
    private DataConvertHelper dataConvertHelper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private WorkDayUtils workDayUtils;

    public void createTaskLog(ApiCreateTaskLogRequest body) {
        TaskLogPo taskLogPo = dataConvertHelper.convert2TaskLogPo(body);
        TaskPo taskPo = taskMapper.selectById(taskLogPo.getTaskId());
        if (null == taskPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12001", taskLogPo.getTaskId());
        }
        if (!taskPo.getUid().equals(taskLogPo.getUid())) {
            //仅任务开发人可以创建任务日志
            throw new BizException(HttpStatus.FORBIDDEN, "auth.11001");
        }
        if (taskLogPo.getUid() != null) {
            UserPo userPo = userMapper.selectById(taskLogPo.getUid());
            if (null == userPo) {
                throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
            }
        }

        taskLogMapper.insert(taskLogPo);
    }

    public Page<TaskLogListBo> getTaskLog(Integer taskId, Integer currentPage, Integer pageSize) {
        QueryWrapper<TaskLogListBo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("taskId", taskId);
        queryWrapper.orderByDesc("createTime");

        Page<TaskLogListBo> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        return taskLogMapper.selectPage(page, queryWrapper);
    }

    public Page<TaskLogAggregationListBo> getTaskLogAggregation(Integer currentPage, Integer pageSize, Integer uid, String log, Integer pid, Integer vid, String sortField, Boolean isAsc, String startTime, String endTime) {
        QueryWrapper<TaskLogAggregationListBo> queryWrapper = new QueryWrapper<>();
        if (null != uid) {
            queryWrapper.eq("tl.uid", uid);
        }
        if (StringUtils.hasText(log)) {
            queryWrapper.like("tl.log", log);
        }
        if (StringUtils.hasText(startTime) && StringUtils.hasText(endTime)) {
            queryWrapper.between("tl.createTime", Utils.convertTimestamp2Date(Long.valueOf(startTime), "yyyy-MM-dd HH:mm:ss"), Utils.convertTimestamp2Date(Long.valueOf(endTime), "yyyy-MM-dd HH:mm:ss"));
        }
        if (null != pid) {
            queryWrapper.eq("p.id", pid);
        }
        if (null != vid) {
            queryWrapper.eq("pv.id", vid);
        }
        if (StringUtils.hasText(sortField)) {
            String[] split = sortField.split(",");
            List<String> sortList = Arrays.asList(split);
            queryWrapper.orderBy(true, isAsc, sortList);
        }

        Page<TaskLogAggregationListBo> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        return taskLogMapper.getTaskLogAggregation(page, queryWrapper);
    }

    public TaskLogPo getLastTaskLog(Integer taskId) {
        TaskPo taskPo = taskMapper.selectById(taskId);
        if (null == taskPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12001", taskId);
        }
        TaskLogPo lastTaskLog = taskLogMapper.getLastTaskLog(taskId);
        if (null == lastTaskLog) {
            throw new BizException(HttpStatus.NOT_FOUND, "taskLog.13001", taskId);
        }
        return lastTaskLog;
    }

    public ArrayList<Integer> getTodayTaskLogUid() {
        long endTime = System.currentTimeMillis();
        long startTime = endTime - 82800000;
        QueryWrapper<Integer> wrapper = new QueryWrapper<>();
        wrapper.between("createTime", Utils.convertTimestamp2Date(startTime, "yyyy-MM-dd HH:mm:ss"), Utils.convertTimestamp2Date(endTime, "yyyy-MM-dd HH:mm:ss"));
        return taskLogMapper.getTodayTaskLogUid(wrapper);
    }

    public Double getWorkTimeSum(Long uid, String exceptId, String startTime, String endTime) {
//        long now = System.currentTimeMillis();
//        String nowString = Utils.convertTimestamp2Date(now, "yyyy-MM-dd HH:mm:ss");
//        String today = nowString.split(" ")[0];
//        String todayStart = today + " " + "00:00:00";
//        String todayEnd = today + " " + "23:59:59";

        QueryWrapper<Integer> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        if (StringUtils.hasText(startTime) && StringUtils.hasText(endTime)) {
            String start = Utils.convertTimestamp2Date(Long.parseLong(startTime), "yyyy-MM-dd HH:mm:ss");
            String end = Utils.convertTimestamp2Date(Long.parseLong(endTime), "yyyy-MM-dd HH:mm:ss");
            wrapper.between("createTime", start, end);
        }
        if (StringUtils.hasText(exceptId)) {
            String[] split = exceptId.split(",");
            List<String> idList = (List<String>) CollectionUtils.arrayToList(split);
            wrapper.notIn("id", idList);
        }
        return taskLogMapper.getWorkTimeSum(wrapper);
    }

    public TaskLogPo updateTaskLog(Long currentUid, Integer id, ApiUpdateTaskLogRequest body) {
        TaskLogPo beforeTaskLogPo = taskLogMapper.selectById(id);
        if (!Objects.equals(currentUid, Long.valueOf(beforeTaskLogPo.getUid()))) {
            throw new BizException(HttpStatus.FORBIDDEN, "auth.11001");
        }
        TaskLogPo taskLogPo = dataConvertHelper.convert2TaskLogPo(body);
        taskLogPo.setId(id);
        taskLogMapper.updateById(taskLogPo);
        return taskLogMapper.selectById(id);
    }

    public ArrayList<String> getLogMissing(Integer uid, String startTime, String endTime) {
        QueryWrapper<String> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.between("createTime", Utils.convertTimestamp2Date(Long.valueOf(startTime), "yyyy-MM-dd HH:mm:ss"), Utils.convertTimestamp2Date(Long.valueOf(endTime), "yyyy-MM-dd HH:mm:ss"));
        ArrayList<String> logDays = taskLogMapper.getLogMissing(wrapper);
        ArrayList<String> logWorkDays = new ArrayList<>();
        //去除周末和节假日
        for (String logDay : logDays) {
            if(!workDayUtils.theDayIsWeekendOrHoliday(logDay)){
                logWorkDays.add(logDay);
            }
        }
        ArrayList<String> workDayLogDays = workDayUtils.getWorkDayStringList(Long.parseLong(startTime), Long.parseLong(endTime));
        return (ArrayList<String>) workDayLogDays.stream().filter(item -> !logWorkDays.contains(item)).collect(Collectors.toList());
    }

}
