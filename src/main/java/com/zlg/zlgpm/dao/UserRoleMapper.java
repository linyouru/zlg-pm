package com.zlg.zlgpm.dao;

import com.zlg.zlgpm.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRoleMapper {

    @Select(" select r.id,r.name,r.describe from role r\n" +
            "        left join user_role ur on(r.id = ur.rid) \n" +
            "        left join user u on(u.id = ur.uid)\n" +
            "        where u.username = #{userName}")
    @Results(id = "role", value = {
            @Result(property = "id", column = "id", javaType = int.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "describe", column = "describe", javaType = String.class)
    })
    List<Role> findByUserName(String userName);
}
