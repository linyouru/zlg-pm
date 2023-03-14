package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.pojo.bo.TaskChangeListBo;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.po.TaskChangePo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskChangeMapper extends BaseMapper<TaskChangePo> {

    @Select("SELECT\n" +
            "\tt.id,\n" +
            "\tt.taskId,\n" +
            "\tp.`name` AS projectName,\n" +
            "\tp.version AS projectVersion,\n"+
            "\tpt.task,\n" +
            "\tt.uid,\n" +
            "\tu.userName,\n" +
            "\tu.nickName,\n" +
            "\tt.auditorId,\n" +
            "\tt.auditorName,\n" +
            "\tt.`status`,\n" +
            "\tt.beforeStartTime,\n" +
            "\tt.beforeEndTime,\n" +
            "\tt.afterStartTime,\n" +
            "\tt.afterEndTime,\n" +
            "\tt.reason,\n" +
            "\tt.createTime\n" +
            "FROM\n" +
            "\t`task_change` AS t\n" +
            "LEFT JOIN `user` AS u ON t.uid = u.id\n" +
            "LEFT JOIN project_task AS pt ON t.taskId = pt.id\n" +
            "LEFT JOIN project AS p ON pt.pid = p.id\n"+
            "${ew.customSqlSegment}")
    Page<TaskChangeListBo> selectPage(Page<TaskChangeListBo> taskChangeListBoPage, @Param(Constants.WRAPPER) Wrapper ew);

}
