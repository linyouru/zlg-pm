package com.zlg.zlgpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.ApiStatisticLogResponseList;
import com.zlg.zlgpm.controller.model.ApiStatisticTaskResponseList;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.bo.StatisticLogBo;
import com.zlg.zlgpm.pojo.bo.StatisticTaskBo;
import com.zlg.zlgpm.service.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linyouru
 * @version 1.0
 * @date 2023/7/11 09:59:41
 */
@RestController
public class StatisticController implements StatisticApi {

    @Resource
    private StatisticService statisticService;
    @Resource
    private DataConvertHelper dataConvertHelper;

    @Override
    public ResponseEntity<ApiStatisticTaskResponseList> statisticTask(Integer uid, Integer pid, Integer vid, String startTime, String endTime, Integer currentPage, Integer pageSize, String sortField, Boolean isAsc) {
        Page<StatisticTaskBo> statisticTaskBoPage = statisticService.statisticTask(uid, pid, vid, startTime, endTime, currentPage, pageSize, sortField, isAsc);
        ApiStatisticTaskResponseList response = dataConvertHelper.convert2ApiStatisticTaskResponseList(statisticTaskBoPage);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ApiStatisticLogResponseList> statisticLog(String startTime, String endTime, Integer uid, Integer currentPage, Integer pageSize) {
        if (!StringUtils.hasText(startTime) && !StringUtils.hasText(endTime)) {
            throw new BizException(HttpStatus.BAD_REQUEST,"statistic.16001");
        }
        Page<StatisticLogBo> statisticLogBoPage = statisticService.statisticLog(uid, startTime, endTime, currentPage, pageSize);
        ApiStatisticLogResponseList response = dataConvertHelper.convert2ApiStatisticLogResponseList(statisticLogBoPage);
        return ResponseEntity.ok().body(response);
    }

}
