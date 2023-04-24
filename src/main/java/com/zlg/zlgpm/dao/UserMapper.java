package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.pojo.bo.TaskListBo;
import com.zlg.zlgpm.pojo.bo.UserListBo;
import com.zlg.zlgpm.pojo.bo.UserMessageBo;
import com.zlg.zlgpm.pojo.po.UserPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<UserPo> {

    Page<UserListBo> queryUserList(Page<UserListBo> userListBoPage, @Param(Constants.WRAPPER) Wrapper<UserPo> ew, String startTime, String endTime);

    @Select("SELECT DISTINCT u.* from `user` AS u LEFT JOIN user_project AS up ON u.id = up.uid ${ew.customSqlSegment}")
    Page<UserListBo> queryUserListByPid(Page<UserListBo> userListBoPage, @Param(Constants.WRAPPER) Wrapper<UserPo> ew);

    @Select("SELECT DISTINCT u.* FROM project AS p LEFT JOIN `user` AS u ON p.uid = u.id ${ew.customSqlSegment}")
    Page<UserListBo> queryUserListByProjectName(Page<UserListBo> userListBoPage, @Param(Constants.WRAPPER) Wrapper<UserPo> ew);

    @Select("SELECT id,email FROM `user` ${ew.customSqlSegment}")
    ArrayList<Map<String, Object>> getUserInfo(@Param(Constants.WRAPPER) Wrapper<Map<String, Object>> ew);

    @Select("SELECT id,userName,nickName FROM `user`;")
    ArrayList<UserPo> getAllUserInfo();

    @Select("SELECT id, userName, nickName, a.acceptNum, b.auditNum FROM `user` AS u LEFT JOIN( SELECT accepterUid, SUM( IF ( `status` = \"2\" OR `status` = \"3\", 1, 0) ) AS acceptNum FROM project_task GROUP BY accepterUid ) AS a ON a.accepterUid = u.id LEFT JOIN ( SELECT auditorId, SUM(IF(`status` = \"1\", 1, 0)) AS auditNum FROM task_change GROUP BY auditorId ) AS b ON b.auditorId = u.id")
    List<UserMessageBo> getUserMessage();
}
