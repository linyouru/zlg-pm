package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlg.zlgpm.pojo.po.UserPo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserPo> {

//    @Select("select * from user")
//    List<User> queryList();
//
//    @Insert("insert into user(id,userName,password,createTime,status) values (#{id},#{userName},#{password},#{createTime},#{status})")
//    int add(User user);
//
//    @Select("select * from user where id =#{id}")
//    @Results(id = "user", value = {
//            @Result(property = "id", column = "id", javaType = int.class),
//            @Result(property = "userName", column = "userName", javaType = String.class),
//            @Result(property = "password", column = "password", javaType = String.class),
//            @Result(property = "createTime", column = "createTime", javaType = String.class),
//            @Result(property = "status", column = "status", javaType = int.class)
//    })
//    User queryById(int id);
//
//
//    @Select("select * from user where userName =#{userName}")
//    @Results(id = "user1", value = {
//            @Result(property = "id", column = "id", javaType = int.class),
//            @Result(property = "userName", column = "userName", javaType = String.class),
//            @Result(property = "password", column = "password", javaType = String.class),
//            @Result(property = "createTime", column = "createTime", javaType = String.class),
//            @Result(property = "status", column = "status", javaType = int.class)
//    })
//    User queryByUserName(String userName);

}
