package com.zlg.zlgpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.ApiCreateTaskRequest;
import com.zlg.zlgpm.controller.model.ApiTaskListResponse;
import com.zlg.zlgpm.controller.model.ApiUpdateTaskRequest;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.service.TaskService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public ResponseEntity<ApiTaskListResponse> taskList(Integer currentPage, Integer pageSize, String status, String projectName, String projectVersion, Integer uid, String startTime, String endTime, String abnormal) {
        Page<TaskListBo> taskListBoPage = taskService.taskList(currentPage, pageSize, status, projectName, projectVersion, uid, startTime, endTime, abnormal);
        ApiTaskListResponse apiTaskListResponse = dataConvertHelper.convert2ApiTaskListResponse(taskListBoPage);
        return ResponseEntity.ok().body(apiTaskListResponse);
    }

    @Override
    public ResponseEntity<Void> updateTask(Integer id, ApiUpdateTaskRequest body) {
        taskService.updateTask(id, body);
        return ResponseEntity.ok(null);
    }
}
