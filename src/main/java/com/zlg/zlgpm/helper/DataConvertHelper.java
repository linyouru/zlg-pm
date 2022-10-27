package com.zlg.zlgpm.helper;

import com.zlg.zlgpm.controller.model.ApiCreateUserRequest;
import com.zlg.zlgpm.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Arrays;

/**
 * 类型转换工具
 * @author linyouru
 */
@Component
public class DataConvertHelper {


    public User convert2User(ApiCreateUserRequest apiCreateUserRequest){
        final User user = new User();
        String password = apiCreateUserRequest.getPassword();
        String passwordMd5 = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setUserName(apiCreateUserRequest.getUserName());
        user.setPassword(passwordMd5);
        user.setNickName(apiCreateUserRequest.getNickName());
        user.setEmail(apiCreateUserRequest.getEmail());
        user.setRemark(apiCreateUserRequest.getRemark());
        return user;
    }


}
