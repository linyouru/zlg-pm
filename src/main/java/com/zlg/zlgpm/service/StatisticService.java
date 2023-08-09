package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.commom.Utils;
import com.zlg.zlgpm.commom.WorkDayUtils;
import com.zlg.zlgpm.controller.model.ApiStatisticWorkTimeResponseList;
import com.zlg.zlgpm.dao.ProjectModuleMapper;
import com.zlg.zlgpm.dao.StatisticTaskMapper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.helper.DataConvertHelper;
import com.zlg.zlgpm.pojo.bo.StatisticLogBo;
import com.zlg.zlgpm.pojo.bo.StatisticTaskBo;
import com.zlg.zlgpm.pojo.bo.StatisticWorkTimeBo;
import com.zlg.zlgpm.pojo.po.ProjectModulePo;
import com.zlg.zlgpm.pojo.po.UserPo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    private ProjectModuleService projectModuleService;
    @Resource
    private DataConvertHelper dataConvertHelper;

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
            task.setTotal(task.getAcceptCount()+task.getFeedbackCount()+task.getTaskTimeoutCount());
            allUserInfo.forEach(user -> {
                if (user.getId() == Long.parseLong(task.getUid() + "")) {
                    task.setNickName(user.getNickName());
                }
            });
        });
        return statisticTaskBoPage;
    }

    public Page<StatisticLogBo> statisticLog(Integer uid, String startTime, String endTime, Integer currentPage, Integer pageSize) {
        QueryWrapper<StatisticLogBo> wrapper = new QueryWrapper<>();
        if (uid != null) {
            wrapper.eq("uid", uid);
        }
        wrapper.between("createTime", Utils.convertTimestamp2Date(Long.valueOf(startTime), "yyyy-MM-dd HH:mm:ss"), Utils.convertTimestamp2Date(Long.valueOf(endTime), "yyyy-MM-dd HH:mm:ss"));

        Page<StatisticLogBo> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);

        Page<StatisticLogBo> statisticLogBoPage = statisticTaskMapper.statisticLog(page, wrapper);
        List<StatisticLogBo> logBoList = statisticLogBoPage.getRecords();
        double days = workDayUtils.getWorkdayTimeInMillisExcWeekendHolidays(Long.parseLong(startTime), Long.parseLong(endTime));
        ArrayList<UserPo> allUserInfo = userMapper.getAllUserInfo();
        ArrayList<HashMap<String, Object>> theoryLogDays = statisticTaskMapper.getTheoryLogDays(wrapper);
        HashMap<String, Integer> userTheoryTotal = new HashMap<>();
        String flag = "";
        int theoryTotal = 0;
        for (HashMap<String, Object> theoryLogDay : theoryLogDays) {
            String userId = theoryLogDay.get("uid") + "";
            if (!Objects.equals(userId, flag)) {
                userTheoryTotal.put(flag, theoryTotal);
                flag = userId;
                theoryTotal = 0;
            }
            if (!workDayUtils.theDayIsWeekendOrHoliday(theoryLogDay.get("day") + ""))
                theoryTotal++;
        }
        userTheoryTotal.put(flag, theoryTotal);

        logBoList.forEach(log -> {
            allUserInfo.forEach(user -> {
                if (user.getId() == Long.parseLong(log.getUid() + "")) {
                    log.setNickName(user.getNickName());
                }
            });
            log.setTheoryTotal(userTheoryTotal.get(log.getUid()+""));
            log.setMiss((int) (days - log.getTheoryTotal()));
            log.setTotal(log.getAbnormal() + log.getMiss());
        });
        return statisticLogBoPage;
    }

    public ApiStatisticWorkTimeResponseList workTimeDetail(Integer pid, Integer vid, Integer uid) {
        QueryWrapper<StatisticWorkTimeBo> wrapper = new QueryWrapper<>();
        if (null != pid) {
            wrapper.eq("pt.pid", pid);
        }
        if (null != vid) {
            wrapper.eq("pt.vid", vid);
        }
        if (null != uid) {
            wrapper.eq("pt.uid", uid);
        }
        //根据条件获取总计划工时
        Integer planWorkTime = statisticTaskMapper.statisticPlanWorkTime(wrapper, pid, vid);
        //根据条件获取总工时
        Integer workTime = statisticTaskMapper.statisticWorkTime(wrapper);
        //根据条件获取按模块分组的工时
        ArrayList<StatisticWorkTimeBo> statisticWorkTimeBos = statisticTaskMapper.statisticWorkTimeDetail(wrapper);
        List<ProjectModulePo> projectModulePos = projectModuleService.queryProjectModule(pid);
        statisticWorkTimeBos.forEach(item -> {
            projectModulePos.forEach(modulePo -> {
                if (Objects.equals(item.getMid(), modulePo.getId())) {
                    item.setModuleName(modulePo.getModule());
                }
            });
        });
        //组装response
        return dataConvertHelper.convert2ApiStatisticWorkTimeResponseList(statisticWorkTimeBos, planWorkTime, workTime);
    }
}
