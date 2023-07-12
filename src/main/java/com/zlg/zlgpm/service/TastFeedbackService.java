package com.zlg.zlgpm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.commom.OperationLog;
import com.zlg.zlgpm.controller.model.ApiStatisticFeedbackResponse;
import com.zlg.zlgpm.dao.TaskFeedbackMapper;
import com.zlg.zlgpm.dao.TaskMapper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.pojo.bo.StatisticFeedbackBo;
import com.zlg.zlgpm.pojo.bo.TaskFeedbackListBo;
import com.zlg.zlgpm.pojo.po.TaskFeedbackPo;
import com.zlg.zlgpm.pojo.po.TaskPo;
import com.zlg.zlgpm.pojo.po.UserPo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TastFeedbackService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private TaskFeedbackMapper feedbackMapper;

    @OperationLog(value = "创建问题反馈", type = "Feedback")
    public TaskFeedbackPo createFeedback(TaskFeedbackPo taskFeedbackPo) {

        UserPo userPo = userMapper.selectById(taskFeedbackPo.getUid());
        if (null == userPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
        }

        TaskPo taskPo = taskMapper.selectById(taskFeedbackPo.getTid());
        if (null == taskPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "task.12001", taskFeedbackPo.getTid());
        }

        feedbackMapper.insert(taskFeedbackPo);
        return taskFeedbackPo;
    }

    @OperationLog(value = "删除问题反馈", type = "Feedback")
    public TaskFeedbackPo deleteFeedback(int id) {
        TaskFeedbackPo taskFeedbackPo = feedbackMapper.selectById(id);
        if (null == taskFeedbackPo) {
            throw new BizException(HttpStatus.BAD_REQUEST, "taskFeedback.14001", id);
        }
        feedbackMapper.deleteById(id);
        return taskFeedbackPo;
    }

    @OperationLog(value = "修改问题反馈", type = "Feedback")
    public TaskFeedbackPo updateFeedback(int id, TaskFeedbackPo body) {
        body.setId(id);
        int i = feedbackMapper.updateById(body);
        if (i == 0) {
            throw new BizException(HttpStatus.BAD_REQUEST, "taskFeedback.14001", id);
        }
        return feedbackMapper.selectById(id);
    }

    public Page<TaskFeedbackListBo> getFeedbackList(Integer tid, Integer currentPage, Integer pageSize) {
        QueryWrapper<TaskFeedbackListBo> wrapper = new QueryWrapper<>();
        wrapper.eq("tid", tid);
        wrapper.orderByDesc("createTime");
        Page<TaskFeedbackListBo> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        return feedbackMapper.getFeedbackList(page, wrapper);
    }

    public Page<StatisticFeedbackBo> getStatisticFeedbackList(Integer pid, Integer vid, Integer uid, String startTime, String endTime, Integer currentPage, Integer pageSize) {
        QueryWrapper<StatisticFeedbackBo> wrapper = new QueryWrapper<>();
        if (pid != null) {
            wrapper.eq("pt.pid", pid);
        }
        if (vid != null) {
            wrapper.eq("pt.vid", vid);
        }
        if (uid != null) {
            wrapper.eq("pt.uid", uid);
        }
        if (StringUtils.hasText(startTime) && StringUtils.hasText(endTime)) {
            wrapper.ge("tf.createTime", Long.parseLong(startTime));
            wrapper.le("tf.createTime", Long.parseLong(endTime));
        }
        Page<StatisticFeedbackBo> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        Page<StatisticFeedbackBo> statisticFeedbackList = feedbackMapper.getStatisticFeedbackList(page, wrapper);
        ArrayList<UserPo> allUserInfo = userMapper.getAllUserInfo();
        statisticFeedbackList.getRecords().forEach(feedback ->{
            allUserInfo.forEach(userInfo->{
                if(Objects.equals(Long.parseLong(feedback.getUid()+""), userInfo.getId())){
                   feedback.setNickName(userInfo.getNickName());
                }
                if(Objects.equals(Long.parseLong(feedback.getAccepterUid()+""), userInfo.getId())){
                    feedback.setAccepterNickName(userInfo.getNickName());
                }
            });
        });
        return statisticFeedbackList;
    }
}
