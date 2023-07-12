package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlg.zlgpm.pojo.po.TaskCheckPo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author linyouru
 * @version 1.0
 * @date 2023/7/10 11:03:31
 */
@Repository
public interface TaskCheckMapper extends BaseMapper<TaskCheckPo> {

    @Select("SELECT * FROM `task_check` WHERE taskId = #{taskId} ORDER BY createTime DESC")
    ArrayList<TaskCheckPo> getTaskCheckByTaskId(Integer taskId);

    @Select("SELECT t.taskId FROM( SELECT taskId,type,isTimely FROM `task_check` WHERE isTimely = 2 AND type = #{type})AS t GROUP BY t.taskId")
    ArrayList<Integer> getNotTimelyTaskId(Integer type);

}
