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

@Repository
public interface UserMapper extends BaseMapper<UserPo> {

//    @Select("SELECT\n" +
//            "\tu.*, t.taskTotal,\n" +
//            "\tt.taskFinishCount,\n" +
//            "\tt.taskTimelyCount,\n" +
//            "\tt.taskTimeoutCount\n" +
//            "\tCONCAT(CAST(FORMAT((t.taskTimelyCount/t.taskFinishCount)*100,2) AS CHAR),\"%\") AS timelinessRate\n"+
//            "FROM\n" +
//            "\t`user` AS u\n" +
//            "LEFT JOIN (\n" +
//            "\tSELECT\n" +
//            "\t\tuid,\n" +
//            "\t\tCOUNT(uid) AS taskTotal,\n" +
//            "\t\tSUM(IF(`status` = \"3\", 1, 0)) AS taskFinishCount,\n" +
//            "\t\tSUM(IF(timely = \"1\", 1, 0)) AS taskTimelyCount,\n" +
//            "\t\tSUM(IF(timely = \"2\", 1, 0)) AS taskTimeoutCount\n" +
//            "\tFROM\n" +
//            "\t\tproject_task\n" +
//            "\t\tWHERE playStartTime >= #{startTime} AND playEndTime <= #{endTime}"+
//            "\tGROUP BY\n" +
//            "\t\tuid\n" +
//            "\tHAVING\n" +
//            "\t\tuid IS NOT NULL\n" +
//            ") AS t ON u.id = t.uid\n" +
//            "${ew.customSqlSegment}\n")
    Page<UserListBo> queryUserList(Page<UserListBo> userListBoPage, @Param(Constants.WRAPPER) Wrapper ew, String startTime, String endTime);

    @Select("SELECT DISTINCT u.* from `user` AS u LEFT JOIN user_project AS up ON u.id = up.uid ${ew.customSqlSegment}")
    Page<UserListBo> queryUserListByPid(Page<UserListBo> userListBoPage, @Param(Constants.WRAPPER) Wrapper ew);
}
