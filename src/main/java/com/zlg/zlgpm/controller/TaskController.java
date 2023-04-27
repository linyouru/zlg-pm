package com.zlg.zlgpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.*;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.bo.ProjectVersionBo;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.bo.TaskStatisticsBo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import com.zlg.zlgpm.pojo.po.TaskRelevancePo;
import com.zlg.zlgpm.pojo.po.UserPo;
import com.zlg.zlgpm.service.TaskChangeService;
import com.zlg.zlgpm.service.TaskRelevanceService;
import com.zlg.zlgpm.service.TaskService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "task")
public class TaskController implements TaskApi {
    @Resource
    private TaskService taskService;
    @Resource
    private DataConvertHelper dataConvertHelper;
    @Resource
    private TaskRelevanceService taskRelevanceService;
    @Resource
    private TaskChangeService taskChangeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ApiBaseResp> createTask(Boolean sandEmail, ApiCreateTaskRequest body) {
        if (body.getUid().equals(body.getAccepterUid())) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12003");
        }
        TaskPo task = taskService.createTask(sandEmail, body);
        if (null != body.getSerialNumber()) {
            //插入新增任务还得改其他任务的序号
            taskService.handleSort(task.getPid(), task.getVid(), body.getSerialNumber(), null, task.getId());
        }
        //建立关联项目关系
        String vidStr = body.getRelevance();
        if (StringUtils.hasText(vidStr)) {
            String[] split = vidStr.split(",");
            ArrayList<TaskRelevancePo> taskRelevancePos = new ArrayList<>();
            for (String vid : split) {
                TaskRelevancePo taskRelevancePo = new TaskRelevancePo();
                taskRelevancePo.setVid(Integer.parseInt(vid));
                taskRelevancePo.setTid(task.getId());
                taskRelevancePos.add(taskRelevancePo);
            }
            taskRelevanceService.saveBatch(taskRelevancePos);
        }
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
    }

    @Override
    public ResponseEntity<Void> deleteTask(Integer id) {


        taskService.deleteTask(id);
        //删除任务关联关系
        taskRelevanceService.deleteTaskRelevanceByTid(id);
        //删除任务变更申请
        taskChangeService.deleteTaskChange(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<ApiProjectVersionsAllResponse>> getTaskRelevance(Integer tid) {
        List<ProjectVersionBo> taskRelevance = taskService.getTaskRelevance(tid);
        List<ApiProjectVersionsAllResponse> responses = dataConvertHelper.convert2ApiProjectVersionsAllResponse(taskRelevance);
        return ResponseEntity.ok().body(responses);
    }

    @Override
    public ResponseEntity<ApiTaskListResponse> getTaskRelevanceList(Integer vid) {
        Page<TaskListBo> taskRelevanceList = taskRelevanceService.getTaskRelevanceList(vid);
        ApiTaskListResponse apiTaskListResponse = dataConvertHelper.convert2ApiTaskListResponse(taskRelevanceList);
        return ResponseEntity.ok().body(apiTaskListResponse);
    }

    @Override
    public ResponseEntity<ApiBaseResp> handleTaskSort(String taskId, Integer newSerialNumber) {
        taskService.handleTaskSort(taskId.split(","), newSerialNumber);
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
    }

    @Override
    public ResponseEntity<ApiTaskResponse> queryTask(Integer id) {
        TaskListBo taskListBo = taskService.queryTask(id);
        if (taskListBo == null) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12001", id);
        }
        ApiTaskResponse response = dataConvertHelper.convert2ApiTaskResponse(taskListBo);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ApiTaskListResponse> taskList(Integer currentPage, Integer pageSize, Integer projectUid, String status,
                                                        Integer pid, Integer vid, Integer uid, String startTime,
                                                        String endTime, String abnormal, String sortField, Boolean isAsc, String mid,
                                                        String level, String task, String detail) {
        if (StringUtils.hasText(sortField) && null == isAsc) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12002");
        }
        Page<TaskListBo> taskListBoPage = taskService.taskList(currentPage, pageSize, projectUid, status, pid, vid, uid,
                startTime, endTime, abnormal, sortField, isAsc, mid, level, task, detail);
        ApiTaskListResponse apiTaskListResponse = dataConvertHelper.convert2ApiTaskListResponse(taskListBoPage);
        return ResponseEntity.ok().body(apiTaskListResponse);
    }

    @Override
    public ResponseEntity<ApiTaskStatisticsResponse> taskStatistics(Integer pid, Integer uid) {
        TaskStatisticsBo taskStatisticsBo = taskService.selectTaskStatistics(pid, uid);
        ApiTaskStatisticsResponse response = dataConvertHelper.convert2ApiTaskStatisticsResponse(taskStatisticsBo);
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Void> updateTask(Integer id, ApiUpdateTaskRequest body) {
        taskService.updateTask(id, body);
        //更新关联项目关系
        String vidStr = body.getRelevance();
        taskRelevanceService.deleteTaskRelevanceByTid(id);
        if (StringUtils.hasText(vidStr)) {
            String[] split = vidStr.split(",");
            ArrayList<TaskRelevancePo> taskRelevancePos = new ArrayList<>();
            for (String vid : split) {
                TaskRelevancePo taskRelevancePo = new TaskRelevancePo();
                taskRelevancePo.setVid(Integer.parseInt(vid));
                taskRelevancePo.setTid(id);
                taskRelevancePos.add(taskRelevancePo);
            }
            taskRelevanceService.saveBatch(taskRelevancePos);
        }
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> taskAccept(Integer id, ApiAcceptTaskRequest body) {
        UserPo currentUser = (UserPo) SecurityUtils.getSubject().getPrincipal();
        TaskPo taskPo = dataConvertHelper.convert2TaskPo(body);
        //仅任务验收人能调该接口
        if (Integer.parseInt(currentUser.getId() + "") != taskPo.getAccepterUid()) {
            throw new BizException(HttpStatus.FORBIDDEN, "auth.11001");
        }
        TaskListBo taskListBo = taskService.queryTask(id);
        if (null == taskListBo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12001", id);
        }
        taskPo.setId(id);
        taskService.taskAccept(taskPo);
        return null;
    }
}
