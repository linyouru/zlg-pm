package com.zlg.zlgpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.ApiCreateTaskChangeRequest;
import com.zlg.zlgpm.controller.model.ApiTaskChangeListResponse;
import com.zlg.zlgpm.controller.model.ApiUpdateTaskChangeRequest;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.bo.TaskChangeListBo;
import com.zlg.zlgpm.service.TaskChangeService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api(tags = "taskChange")
public class TaskChangeContorller implements TaskChangeApi {

    @Resource
    private TaskChangeService taskChangeService;
    @Resource
    private DataConvertHelper dataConvertHelper;

    @Override
    public ResponseEntity<Void> createTaskChange(ApiCreateTaskChangeRequest body) {
        taskChangeService.createTaskChange(body);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<ApiTaskChangeListResponse> getTaskChange(Integer taskId, Integer auditorId, String status, Integer currentPage, Integer pageSize, String sortField, Boolean isAsc) {
        Page<TaskChangeListBo> taskChange = taskChangeService.getTaskChange(taskId, auditorId, status, currentPage, pageSize, sortField, isAsc);
        ApiTaskChangeListResponse apiTaskChangeListResponse = dataConvertHelper.convert2ApiTaskChangeListResponse(taskChange);
        return ResponseEntity.ok().body(apiTaskChangeListResponse);
    }

    @Override
    public ResponseEntity<Void> updateTaskChange(ApiUpdateTaskChangeRequest body, Integer id) {
        taskChangeService.updateTaskChange(body,id);
        return ResponseEntity.ok(null);
    }

}
