package com.zlg.zlgpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.ApiCreateFeedbackRequest;
import com.zlg.zlgpm.controller.model.ApiFeedbackListResponse;
import com.zlg.zlgpm.controller.model.ApiUpdateFeedbackRequest;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.bo.TaskFeedbackListBo;
import com.zlg.zlgpm.pojo.po.TaskFeedbackPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import com.zlg.zlgpm.service.TastFeedbackService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "taskFeedback")
public class TastFeedbackController implements TaskFeedbackApi {

    @Resource
    private DataConvertHelper dataConvertHelper;
    @Resource
    private TastFeedbackService feedbackService;

    @Override
    public ResponseEntity<Void> createFeedback(ApiCreateFeedbackRequest body) {
        TaskFeedbackPo taskFeedbackPo = dataConvertHelper.convert2TaskFeedbackPo(body);
        UserPo currentUser = (UserPo) SecurityUtils.getSubject().getPrincipal();
        if(Integer.parseInt(currentUser.getId()+"")!=taskFeedbackPo.getUid()){
            throw new BizException(HttpStatus.FORBIDDEN,"auth.11001");
        }
        feedbackService.createFeedback(taskFeedbackPo);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> deleteFeedback(Integer id) {
//        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> updateFeedback(Integer id, ApiUpdateFeedbackRequest body) {
//        TaskFeedbackPo taskFeedbackPo = dataConvertHelper.convert2TaskFeedbackPo(body);
//        feedbackService.updateFeedback(id,taskFeedbackPo);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<ApiFeedbackListResponse> getFeedbackList(Integer tid, Integer currentPage, Integer pageSize) {
        Page<TaskFeedbackListBo> feedbackList = feedbackService.getFeedbackList(tid, currentPage, pageSize);
        ApiFeedbackListResponse apiFeedbackListResponse = dataConvertHelper.convert2ApiFeedbackListResponse(feedbackList);
        return ResponseEntity.ok().body(apiFeedbackListResponse);
    }
}
