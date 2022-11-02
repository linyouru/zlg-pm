package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlg.zlgpm.pojo.po.RolePo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<RolePo> {

    @Select(" select r.id,r.name,r.remark from role r\n" +
            "        left join user_role ur on(r.id = ur.rid) \n" +
            "        left join user u on(u.id = ur.uid)\n" +
            "        where u.username = #{userName}")
    @Results(id = "role", value = {
            @Result(property = "id", column = "id", javaType = int.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "remark", column = "remark", javaType = String.class)
    })
    List<RolePo> findByUserName(String userName);

}
