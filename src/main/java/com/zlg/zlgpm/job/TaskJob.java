package com.zlg.zlgpm.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlg.zlgpm.commom.WorkDayUtils;
import com.zlg.zlgpm.dao.TaskMapper;
import com.zlg.zlgpm.helper.EmailHelper;
import com.zlg.zlgpm.pojo.po.TaskPo;
import com.zlg.zlgpm.service.TaskLogService;
import com.zlg.zlgpm.service.TaskService;
import com.zlg.zlgpm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 任务相关定时任务
 *
 * @author linyouru
 */
@EnableScheduling
@Component
public class TaskJob {


    @Resource
    private EmailHelper emailHelper;
    @Resource
    private TaskLogService taskLogService;
    @Resource
    private UserService userService;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private WorkDayUtils workDayUtils;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 每天23点检查当天所有人任务日志，没写的就发邮件提醒一下
     */
    @Scheduled(cron = "${cron.email}")
    private void taskLogSendEmailJob() {
        //获取普通人员列表(除了叶)
        ArrayList<Map<String, Object>> userInfoList = userService.getUserInfo();
        //获取今日写了任务日志的uid列表
        ArrayList<Integer> todayTaskLogUidList = taskLogService.getTodayTaskLogUid();
        //筛选出没写日志的人
        for (Map<String, Object> userInfo : userInfoList) {
            if (!todayTaskLogUidList.contains(Integer.parseInt(userInfo.get("id") + ""))) {
                //发邮件通知
                SimpleMailMessage message = emailHelper.getSimpleMailMessage(EmailHelper.EMAIL_FORM, userInfo.get("email") + "",
                        "[项目管理系统] 任务日志填写提醒", "您今日没有填写任务日志，请及时补充，谢谢~");
                emailHelper.sendSimpleMailMessage(message);
            }
        }
    }

    /***
     * 计算任务及时性和延期天数
     * @author linyouru
     * @date 2023/8/9 11:12:57
     */
    @Scheduled(cron = "${cron.timely}")
//    @Scheduled(cron = "0/30 * * * * ? ")
    private void taskTimelyJob() {
        QueryWrapper<TaskPo> wrapper = new QueryWrapper<>();
        wrapper.eq("status", "4");
        List<TaskPo> taskPos = taskMapper.selectList(wrapper);
        long now = System.currentTimeMillis();
        taskPos.forEach(taskPo -> {
            long playEndTime = Long.parseLong(taskPo.getPlayEndTime());
            if (now < playEndTime) {
                //未延期
                taskPo.setTimely("1");
                taskPo.setDelayDayCount(0);
            } else {
                taskPo.setTimely("2");
                taskPo.setDelayDayCount((int) workDayUtils.getWorkdayTimeInMillisExcWeekendHolidays(playEndTime,now));
            }
            taskMapper.updateById(taskPo);
        });
    }

}
