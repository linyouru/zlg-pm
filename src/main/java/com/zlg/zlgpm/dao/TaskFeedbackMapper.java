package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.pojo.bo.TaskFeedbackListBo;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.po.TaskFeedbackPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskFeedbackMapper extends BaseMapper<TaskFeedbackPo> {


    @Select("SELECT\n" +
            "\ttf.*, u.nickName\n" +
            "FROM\n" +
            "\ttask_feedback AS tf\n" +
            "LEFT JOIN `user` AS u ON tf.uid = u.id\n" +
            "\t${ew.customSqlSegment}\n")
    Page<TaskFeedbackListBo> getFeedbackList(Page<TaskFeedbackListBo> taskFeedbackListBoPage, @Param(Constants.WRAPPER) Wrapper<TaskFeedbackListBo> ew);


}
