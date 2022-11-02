package com.zlg.zlgpm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlg.zlgpm.pojo.po.PermissionPo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends BaseMapper<PermissionPo> {

    @Select("select p.id,p.url,p.name,p.remark from role r\n" +
            "        left join user_role ur on(r.id = ur.rid) \n" +
            "        left join user u on(u.id = ur.uid)\n" +
            "        left join role_permission rp on(rp.rid = r.id) \n" +
            "        left join permission p on(p.id = rp.pid ) \n" +
            "        where u.username = #{userName}")
    @Results(id = "permission", value = {
            @Result(property = "id", column = "id", javaType = int.class),
            @Result(property = "url", column = "url", javaType = String.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "remark", column = "remark", javaType = String.class)
    })
    List<PermissionPo> findByUserName(String userName);
}
