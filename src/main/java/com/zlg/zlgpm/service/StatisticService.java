package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.dao.StatisticTaskMapper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.pojo.bo.StatisticTaskBo;
import com.zlg.zlgpm.pojo.po.UserPo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    public Page<StatisticTaskBo> statisticTask(Integer uid, Integer pid, Integer vid, String startTime, String endTime, Integer currentPage, Integer pageSize) {
        QueryWrapper<StatisticTaskBo> wrapper = new QueryWrapper<>();
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

        Page<StatisticTaskBo> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);

        Page<StatisticTaskBo> statisticTaskBoPage = statisticTaskMapper.statisticTask(page, wrapper);
        List<StatisticTaskBo> statisticTaskBoList = statisticTaskBoPage.getRecords();

        ArrayList<UserPo> allUserInfo = userMapper.getAllUserInfo();
        statisticTaskBoList.forEach(task -> {
            allUserInfo.forEach(user -> {
                if (user.getId() == Long.parseLong(task.getUid() + "")) {
                    task.setNickName(user.getNickName());
                }
            });
            Integer total = 0;
            if (null != task.getTaskTimeoutCount()) {
                total += task.getTaskTimeoutCount();
            }
            if (null != task.getFeedbackCount()) {
                total += task.getFeedbackCount();
            }
            if (null != task.getAcceptCount()) {
                total += task.getAcceptCount();
            }
            task.setTotal(total);
        });
        return statisticTaskBoPage;
    }


}
