package com.zlg.zlgpm.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl  {

    @Resource
    private UserMapper userMapper;

    public Page<User> queryList() {
        return PageHelper.startPage(1, 2).doSelectPage(() -> this.userMapper.queryList());
    }

    public int add(User user) {
        return this.userMapper.add(user);
    }

    public User queryById(int id) {
        return this.userMapper.queryById(id);
    }

    public User queryByUserName(String userName) {
        return this.userMapper.queryByUserName(userName);
    }


}
