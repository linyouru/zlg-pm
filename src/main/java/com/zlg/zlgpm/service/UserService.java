package com.zlg.zlgpm.service;

import com.zlg.zlgpm.entity.User;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.controller.model.ApiCreateUserRequest;
import com.zlg.zlgpm.dao.UserMapper;
import com.zlg.zlgpm.helper.DataConvertHelper;
import io.mybatis.mapper.example.Example;
import io.mybatis.mapper.fn.Fn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserMapper userMapper;
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

    public void createUser(ApiCreateUserRequest apiCreateUserRequest) {
        long timeMillis = System.currentTimeMillis();
//        throw new BizException(HttpStatus.BAD_REQUEST,"tenant.10003");
        User user = dataConvertHelper.convert2User(apiCreateUserRequest);
        user.setCreateTime(timeMillis + "");
        user.setUpdateTime(timeMillis + "");
        user.setStatus(1);
        User user1 = new User();
        user1.setUserName(user.getUserName());
        long count = userMapper.selectCount(user1);
        if (count > 0) {

        }
//        int insert = userMapper.insert(user);

    }


}
