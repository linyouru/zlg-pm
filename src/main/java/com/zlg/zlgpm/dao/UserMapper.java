package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.bo.UserListBo;
import com.zlg.zlgpm.pojo.po.UserPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<UserPo> {

    Page<UserListBo> queryUserList(Page<UserListBo> userListBoPage, @Param(Constants.WRAPPER) Wrapper<UserPo> ew, String startTime, String endTime);

    @Select("SELECT DISTINCT u.* from `user` AS u LEFT JOIN user_project AS up ON u.id = up.uid ${ew.customSqlSegment}")
    Page<UserListBo> queryUserListByPid(Page<UserListBo> userListBoPage, @Param(Constants.WRAPPER) Wrapper<UserPo> ew);

    @Select("SELECT DISTINCT u.* FROM project AS p LEFT JOIN `user` AS u ON p.uid = u.id ${ew.customSqlSegment}")
    Page<UserListBo> queryUserListByProjectName(Page<UserListBo> userListBoPage, @Param(Constants.WRAPPER) Wrapper<UserPo> ew);

    @Select("SELECT id,email FROM `user` ${ew.customSqlSegment}")
    ArrayList<Map<String,Object>> getUserInfo(@Param(Constants.WRAPPER) Wrapper<Map<String,Object>> ew);
}
