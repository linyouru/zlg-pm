package com.zlg.zlgpm.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zlg.zlgpm.controller.model.ApiUpdateUserRequest;
import com.zlg.zlgpm.controller.model.ApiUserListResponse;
import com.zlg.zlgpm.controller.model.ApiUserResponse;
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
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private DataConvertHelper dataConvertHelper;

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

    public void updateUser(Integer id, ApiUpdateUserRequest body) {
        Long count = userMapper.selectCount(new QueryWrapper<User>().eq("id", id));
        if (count < 1) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
        }
        User user = dataConvertHelper.convert2User(body);
        user.setId(id.longValue());
        int i = userMapper.updateById(user);
    }

    public void updatePassword(Integer id, String newPassword, String oldPassword) {
        User select = userMapper.selectById(id);
        if (null == select) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10002");
        } else if (!select.getPassword().equals(DigestUtils.md5DigestAsHex(oldPassword.getBytes()))) {
            throw new BizException(HttpStatus.BAD_REQUEST, "user.10003");
        }
        User user = new User();
        user.setId(Long.valueOf(id));
        user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        userMapper.updateById(user);
    }

    public ApiUserListResponse userList(String userName, Integer currentPage, Integer pageSize) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ne("id", 1);
        if (null != userName) {
            wrapper.eq("userName", userName);
        }
        Page<User> userPage = userMapper.selectPage(new Page<User>().setCurrent(currentPage).setSize(pageSize), wrapper);
        return dataConvertHelper.convert2ApiUserListResponse(userPage);
    }

    public ApiUserResponse queryUserByName(String userName){
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("userName", userName));
        return dataConvertHelper.convert2UserResponse(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Integer id) {
        userMapper.delete(new QueryWrapper<User>().eq("id", id));
        userRoleMapper.delete(new QueryWrapper<UserRole>().eq("uid", id));
    }


}
