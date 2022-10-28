package com.zlg.zlgpm.helper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zlg.zlgpm.controller.model.*;
import com.zlg.zlgpm.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 类型转换工具
 *
 * @author linyouru
 */
@Component
public class DataConvertHelper {


    public User convert2User(ApiCreateUserRequest apiCreateUserRequest) {
        final User user = new User();
        String password = apiCreateUserRequest.getPassword();
        user.setUserName(apiCreateUserRequest.getUserName());
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        user.setNickName(apiCreateUserRequest.getNickName());
        user.setEmail(apiCreateUserRequest.getEmail());
        user.setRemark(apiCreateUserRequest.getRemark());
        return user;
    }

    public User convert2User(ApiUpdateUserRequest apiUpdateUserRequest) {
        User user = new User();
        String password = apiUpdateUserRequest.getPassword();
        if (null != password) {
            user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        }
        user.setNickName(apiUpdateUserRequest.getNickName());
        user.setEmail(apiUpdateUserRequest.getEmail());
        user.setRemark(apiUpdateUserRequest.getRemark());
        return user;
    }

    public ApiUserResponse convert2UserResponse(User user) {
        ApiUserResponse apiUserResponse = new ApiUserResponse();
        apiUserResponse.setId(Math.toIntExact(user.getId()));
        apiUserResponse.setUserName(user.getUserName());
        apiUserResponse.setNickName(user.getNickName());
        apiUserResponse.setEmail(user.getEmail());
        apiUserResponse.setRemark(user.getRemark());
        apiUserResponse.setCreateTime(user.getCreateTime());
        apiUserResponse.setUpdateTime(user.getUpdateTime());
        return apiUserResponse;
    }

    public ApiUserListResponse convert2ApiUserListResponse(Page<User> userPage) {
        ApiUserListResponse apiUserResponse = new ApiUserListResponse();
        fillApiPage(apiUserResponse,userPage);
        apiUserResponse.setList(userPage.getRecords().stream().map(this::convert2UserResponse).collect(Collectors.toList()));
        return apiUserResponse;
    }

    public static <T> void fillApiPage(ApiOnePageData rst, Page<T> pd) {
        final ApiOnePageDataPagination pagination = new ApiOnePageDataPagination();
        pagination.setTotalSize((int) pd.getTotal());
        pagination.setCurrentPage((int) pd.getCurrent());
        pagination.setPageSize((int) pd.getSize());
        rst.setPagination(pagination);
    }


}
