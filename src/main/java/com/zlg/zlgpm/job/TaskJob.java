package com.zlg.zlgpm.job;

import com.zlg.zlgpm.helper.EmailHelper;
import com.zlg.zlgpm.service.TaskLogService;
import com.zlg.zlgpm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 每天23点检查当天所有人任务日志，没写的就发邮件提醒一下
     */
    @Scheduled(cron = "${cron.exp}")
    private void taskTimelyJob() {
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
                System.out.println(message.toString());
                emailHelper.sendSimpleMailMessage(message);
            }
        }
    }

}
