package com.zlg.zlgpm.controller;


import com.zlg.zlgpm.controller.model.ApiOperationLogListResponse;
import com.zlg.zlgpm.pojo.po.UserPo;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "operationLog")
public class OperationLogController implements OperationLogApi{

    @Override
    public ResponseEntity<ApiOperationLogListResponse> operationLogList(Integer uid, String record, String startTime, String endTime) {
        UserPo currentUser = (UserPo) SecurityUtils.getSubject().getPrincipal();
        return null;
    }
}
