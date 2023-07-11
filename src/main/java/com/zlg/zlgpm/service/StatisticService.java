package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.commom.Utils;
import com.zlg.zlgpm.commom.WorkDayUtils;
import com.zlg.zlgpm.dao.StatisticTaskMapper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.pojo.bo.StatisticLogBo;
import com.zlg.zlgpm.pojo.bo.StatisticTaskBo;
import com.zlg.zlgpm.pojo.po.UserPo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author linyouru
 * @version 1.0
 * @date 2023/7/11 10:06:25
 */
@Service
public class StatisticService {

    @Resource
    private StatisticTaskMapper statisticTaskMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private WorkDayUtils workDayUtils;

    public Page<StatisticTaskBo> statisticTask(Integer uid, Integer pid, Integer vid, String startTime, String endTime, Integer currentPage, Integer pageSize, String sortField, Boolean isAsc) {
        QueryWrapper<StatisticTaskBo> wrapper = new QueryWrapper<>();
        List<String> sortList = null;
        if (null != uid) {
            wrapper.eq("t.uid", uid);
        }
        if (null != pid) {
            wrapper.eq("t.pid", pid);
        }
        if (null != vid) {
            wrapper.eq("t.vid", vid);
        }
        if (StringUtils.hasText(startTime) && StringUtils.hasText(endTime)) {
            wrapper.ge("t.playStartTime", startTime);
            wrapper.le("t.playEndTime", endTime);
        }
        if (StringUtils.hasText(sortField)) {
            String[] split = sortField.split(",");
            sortList = Arrays.asList(split);
        }

        Page<StatisticTaskBo> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);

        Page<StatisticTaskBo> statisticTaskBoPage = statisticTaskMapper.statisticTask(page, wrapper, sortList, isAsc);
        List<StatisticTaskBo> statisticTaskBoList = statisticTaskBoPage.getRecords();

        ArrayList<UserPo> allUserInfo = userMapper.getAllUserInfo();
        statisticTaskBoList.forEach(task -> {
            allUserInfo.forEach(user -> {
                if (user.getId() == Long.parseLong(task.getUid() + "")) {
                    task.setNickName(user.getNickName());
                }
            });
        });
        return statisticTaskBoPage;
    }

    public  Page<StatisticLogBo> statisticLog(Integer uid, String startTime, String endTime, Integer currentPage, Integer pageSize){
        QueryWrapper<StatisticLogBo> wrapper = new QueryWrapper<>();
        if(uid != null){
            wrapper.eq("uid",uid);
        }
        wrapper.between("createTime", Utils.convertTimestamp2Date(Long.valueOf(startTime), "yyyy-MM-dd HH:mm:ss"), Utils.convertTimestamp2Date(Long.valueOf(endTime), "yyyy-MM-dd HH:mm:ss"));

        Page<StatisticLogBo> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);

        Page<StatisticLogBo> statisticLogBoPage = statisticTaskMapper.statisticLog(page, wrapper);
        List<StatisticLogBo> logBoList = statisticLogBoPage.getRecords();
        double days = workDayUtils.getWorkdayTimeInMillisExcWeekend(Long.parseLong(startTime), Long.parseLong(endTime));
        ArrayList<UserPo> allUserInfo = userMapper.getAllUserInfo();
        logBoList.forEach(log->{
            allUserInfo.forEach(user->{
                if(user.getId() == Long.parseLong(log.getUid() + "")){
                    log.setNickName(user.getNickName());
                }
            });
            log.setMiss((int) (days-log.getTheoryTotal()));
            log.setTotal(log.getAbnormal()+log.getMiss());
        });
        return statisticLogBoPage;
    }

}
