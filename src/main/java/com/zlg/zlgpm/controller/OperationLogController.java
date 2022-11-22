package com.zlg.zlgpm.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.ApiOperationLogListResponse;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.po.OperationLogPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import com.zlg.zlgpm.service.OperationLogService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "operationLog")
public class OperationLogController implements OperationLogApi{

    @Resource
    OperationLogService operationLogService;

    @Resource
    DataConvertHelper dataConvertHelper;

    @Override
    public ResponseEntity<ApiOperationLogListResponse> operationLogList(Integer uid, String record, String startTime, String endTime, Integer currentPage, Integer pageSize) {
        Page<OperationLogPo> operationLogPoPage = operationLogService.operationLogList(currentPage, pageSize, uid, record, startTime, endTime);
        ApiOperationLogListResponse apiOperationLogListResponse = dataConvertHelper.convert2ApiOperationLogListResponse(operationLogPoPage);
        return ResponseEntity.ok().body(apiOperationLogListResponse);
    }
}
