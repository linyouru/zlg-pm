package com.zlg.zlgpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.ApiCreateTaskLogRequest;
import com.zlg.zlgpm.controller.model.ApiLastTaskLogResponse;
import com.zlg.zlgpm.controller.model.ApiTaskLogAggregationListResponse;
import com.zlg.zlgpm.controller.model.ApiTaskLogListResponse;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.bo.TaskLogAggregationListBo;
import com.zlg.zlgpm.pojo.bo.TaskLogListBo;
import com.zlg.zlgpm.pojo.po.TaskLogPo;
import com.zlg.zlgpm.service.TaskLogService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "taskLog")
public class TaskLogController implements TaskLogApi {
    @Resource
    private TaskLogService taskLogService;
    @Resource
    private DataConvertHelper dataConvertHelper;

    @Override
    public ResponseEntity<Void> createTaskLog(ApiCreateTaskLogRequest body) {
        taskLogService.createTaskLog(body);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<ApiLastTaskLogResponse> getLastTaskLog(Integer taskId) {
        TaskLogPo lastTaskLog = taskLogService.getLastTaskLog(taskId);
        ApiLastTaskLogResponse response = dataConvertHelper.convert2ApiLastTaskLogResponse(lastTaskLog);
        return ResponseEntity.ok().body(response);
    }


    @Override
    public ResponseEntity<ApiTaskLogListResponse> getTaskLog(Integer taskId, Integer currentPage, Integer pageSize) {
        Page<TaskLogListBo> taskLog = taskLogService.getTaskLog(taskId, currentPage, pageSize);
        ApiTaskLogListResponse response = dataConvertHelper.convert2ApiTaskLogListResponse(taskLog);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ApiTaskLogAggregationListResponse> getTaskLogAggregation(Integer currentPage, Integer pageSize, Integer uid, String log, Integer pid, String sortField, Boolean isAsc, String startTime, String endTime) {
        if(StringUtils.hasText(sortField)&&isAsc ==null){
            throw new BizException(HttpStatus.BAD_REQUEST,"task.12002");
        }
        Page<TaskLogAggregationListBo> taskLogAggregation = taskLogService.getTaskLogAggregation(currentPage, pageSize, uid, log, pid, sortField, isAsc, startTime, endTime);
        ApiTaskLogAggregationListResponse response = dataConvertHelper.convertToAggregationListResponse(taskLogAggregation);
        return ResponseEntity.ok().body(response);
    }


}
