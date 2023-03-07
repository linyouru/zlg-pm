package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zlg.zlgpm.pojo.po.UserProjectPo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProjectMapper extends BaseMapper<UserProjectPo> {

    @Delete("DELETE FROM `user_project` ${ew.customSqlSegment}")
    void deleteProjectUser(@Param(Constants.WRAPPER) Wrapper ew);

}
