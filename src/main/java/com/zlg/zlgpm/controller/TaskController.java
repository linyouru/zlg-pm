package com.zlg.zlgpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.ApiCreateTaskRequest;
import com.zlg.zlgpm.controller.model.ApiTaskListResponse;
import com.zlg.zlgpm.controller.model.ApiTaskStatisticsResponse;
import com.zlg.zlgpm.controller.model.ApiUpdateTaskRequest;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.bo.TaskStatisticsBo;
import com.zlg.zlgpm.service.TaskService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "task")
public class TaskController implements TaskApi {
    @Resource
    private TaskService taskService;
    @Resource
    private DataConvertHelper dataConvertHelper;

    @Override
    public ResponseEntity<Void> createTask(ApiCreateTaskRequest body) {
        taskService.createTask(body);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> deleteTask(Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<String>> taskGroupModule(String projectName, String projectVersion) {
        List<Map<String, String>> maps = taskService.aggregatedTaskModule(projectName, projectVersion);
        List<String> responses = dataConvertHelper.convert2ApiTaskGroupModuleResponse(maps);
        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<ApiTaskListResponse> taskList(Integer currentPage, Integer pageSize, String status, String projectName, String projectVersion, Integer uid, String startTime, String endTime, String abnormal, String sortField, Boolean isAsc, String module) {
        if (StringUtils.hasText(sortField) && null == isAsc) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12002");
        }
        Page<TaskListBo> taskListBoPage = taskService.taskList(currentPage, pageSize, status, projectName, projectVersion, uid, startTime, endTime, abnormal, sortField, isAsc, module);
        ApiTaskListResponse apiTaskListResponse = dataConvertHelper.convert2ApiTaskListResponse(taskListBoPage);
        return ResponseEntity.ok().body(apiTaskListResponse);
    }

    @Override
    public ResponseEntity<ApiTaskStatisticsResponse> taskStatistics() {
        TaskStatisticsBo taskStatisticsBo = taskService.selectTaskStatistics();
        ApiTaskStatisticsResponse response = dataConvertHelper.convert2ApiTaskStatisticsResponse(taskStatisticsBo);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> updateTask(Integer id, ApiUpdateTaskRequest body) {
        taskService.updateTask(id, body);
        return ResponseEntity.ok(null);
    }
}
