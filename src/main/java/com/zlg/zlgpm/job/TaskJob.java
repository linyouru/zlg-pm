package com.zlg.zlgpm.job;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zlg.zlgpm.pojo.po.TaskPo;
import com.zlg.zlgpm.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 任务相关定时任务
 *
 * @author linyouru
 */
@EnableScheduling
@Component
public class TaskJob {

    @Resource
    private TaskService taskService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 定时计算任务及时性
     */
    @Scheduled(cron = "0 0 0 ? * ? ")
    private void taskTimelyJob() {
        UpdateWrapper<TaskPo> updateWrapper = new UpdateWrapper();
        updateWrapper.ne("status", "3");
        updateWrapper.ge("playEndTime", System.currentTimeMillis());
        TaskPo taskPo = new TaskPo();
        taskPo.setTimely("1");
        taskService.updateTaskTimely(taskPo, updateWrapper);

        UpdateWrapper<TaskPo> updateWrapper2 = new UpdateWrapper();
        updateWrapper2.ne("status", "3");
        updateWrapper2.lt("playEndTime", System.currentTimeMillis());
        taskPo.setTimely("2");
        taskService.updateTaskTimely(taskPo, updateWrapper2);
        System.out.println("refresh task timely job end");
        logger.info("refresh task timely job end");
    }

}
