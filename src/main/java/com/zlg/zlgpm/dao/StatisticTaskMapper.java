package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.pojo.bo.StatisticTaskBo;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author linyouru
 * @version 1.0
 * @date 2023/7/11 10:16:13
 */
@Repository
public interface StatisticTaskMapper extends BaseMapper<StatisticTaskBo> {

    Page<StatisticTaskBo> statisticTask(Page<StatisticTaskBo> page, @Param(Constants.WRAPPER) Wrapper<StatisticTaskBo> ew, List<String> sortList, Boolean isAsc);
}
