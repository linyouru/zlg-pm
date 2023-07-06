package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.commom.Utils;
import com.zlg.zlgpm.dao.OperationLogMapper;
import com.zlg.zlgpm.pojo.po.OperationLogPo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service
public class OperationLogService {

    @Resource
    private OperationLogMapper operationLogMapper;

    public void createOperationLog(OperationLogPo operationLog) {
        int insert = operationLogMapper.insert(operationLog);
    }

    public Page<OperationLogPo> operationLogList(Integer currentPage, Integer pageSize, Integer uid, String record, String startTime, String endTime) {
        Page<OperationLogPo> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        QueryWrapper<OperationLogPo> wrapper = new QueryWrapper<>();
        if (null != uid) {
            wrapper.eq("uid", uid);
        }
        if (StringUtils.hasText(record)) {
            wrapper.like("record", record);
        }
        if (StringUtils.hasText(startTime) && StringUtils.hasText(endTime)) {
            wrapper.between("createTime", Utils.convertTimestamp2Date(Long.valueOf(startTime), "yyyy-MM-dd HH:mm:ss"), Utils.convertTimestamp2Date(Long.valueOf(endTime), "yyyy-MM-dd HH:mm:ss"));
        }
        wrapper.orderByDesc("createTime");
        return operationLogMapper.selectPage(page, wrapper);
    }



}
