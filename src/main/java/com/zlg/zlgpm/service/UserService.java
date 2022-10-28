package com.zlg.zlgpm.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zlg.zlgpm.dao.UserRoleMapper;
import com.zlg.zlgpm.entity.User;
import com.zlg.zlgpm.entity.UserRole;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.controller.model.ApiCreateUserRequest;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.helper.DataConvertHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private DataConvertHelper dataConvertHelper;

//    public Page<User> queryList() {
//        return PageHelper.startPage(1, 2).doSelectPage(() -> this.userMapper.queryList());
//    }
//
//    public int add(User user) {
//        return this.userMapper.add(user);
//    }
//
//    public User queryById(int id) {
//        return this.userMapper.queryById(id);
//    }
//
//    public User queryByUserName(String userName) {
//        return this.userMapper.queryByUserName(userName);
//    }

    @Transactional(rollbackFor = Exception.class)
    public void createUser(ApiCreateUserRequest apiCreateUserRequest) {
        User user = dataConvertHelper.convert2User(apiCreateUserRequest);
        user.setStatus(1);
        Long count = userMapper.selectCount(new QueryWrapper<User>().eq("username", user.getUserName()));
        if (count > 0) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10001");
        }
        int insert = userMapper.insert(user);
        User insertUser = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUserName()));
        //添加默认角色
        userRoleMapper.insert(new UserRole(insertUser.getId(), 3L));
    }


}
