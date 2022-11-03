package com.zlg.zlgpm.controller;

import com.zlg.zlgpm.controller.model.ApiCreateTaskRequest;
import com.zlg.zlgpm.controller.model.ApiTaskListResponse;
import com.zlg.zlgpm.controller.model.ApiUpdateTaskRequest;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags="task")
public class TaskController implements TaskApi{
    @Override
    public ResponseEntity<Void> createTask(Integer pid, Integer uid, ApiCreateTaskRequest body) {

        return null;
    }

    @Override
    public ResponseEntity<Void> deleteTask(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<ApiTaskListResponse> taskList(String status, String projectName, String projectVersion, Integer uid, String startTime, String endTime, String abnormal) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateTask(Integer id, ApiUpdateTaskRequest body) {
        return null;
    }
}
