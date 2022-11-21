package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.dao.OperationLogMapper;
import com.zlg.zlgpm.pojo.po.OperationLogPo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

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
        if(null != uid){
            wrapper.eq("uid",uid);
        }
        if(StringUtils.hasText(record)){
            wrapper.like("record",record);
        }
        if (StringUtils.hasText(startTime) && StringUtils.hasText(endTime)) {
            wrapper.ge("createTime", Long.parseLong(startTime));
            wrapper.le("createTime", Long.parseLong(endTime));
        }
        return operationLogMapper.selectPage(page, wrapper);
    }

}
