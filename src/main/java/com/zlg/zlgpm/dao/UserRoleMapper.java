package com.zlg.zlgpm.dao;

import com.zlg.zlgpm.entity.Role;
import com.zlg.zlgpm.entity.UserRole;
import io.mybatis.mapper.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@org.apache.ibatis.annotations.Mapper
public interface UserRoleMapper extends Mapper<UserRole,Long> {


}
