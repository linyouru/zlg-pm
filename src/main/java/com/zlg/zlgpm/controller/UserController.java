package com.zlg.zlgpm.controller;


import com.zlg.zlgpm.controller.model.ApiBaseResp;
import com.zlg.zlgpm.controller.model.ApiCreateUserRequest;
import com.zlg.zlgpm.controller.model.ApiUpdateUserRequest;
import com.zlg.zlgpm.controller.model.ApiUserListResponse;
import com.zlg.zlgpm.service.UserService;
import io.swagger.annotations.Api;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "user", description = "用户相关接口")
public class UserController implements UserApi {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    @Override
    @RequiresRoles(value = "root")
    public ResponseEntity<ApiBaseResp> createUser(ApiCreateUserRequest body) {
        userService.createUser(body);
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
    }

    @Override
    @RequiresRoles(value = "root")
    public ResponseEntity<ApiBaseResp> deleteUser(Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
    }

    @Override
    @RequiresRoles(value = "root")
    public ResponseEntity<ApiBaseResp> updateUser(Integer id, ApiUpdateUserRequest body) {
        userService.updateUser(id, body);
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
    }

    @Override
    public ResponseEntity<ApiBaseResp> updateUserPassword(Integer id, String newPassword, String oldPassword) {
        userService.updatePassword(id, newPassword, oldPassword);
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
    }

    @Override
    public ResponseEntity<ApiUserListResponse> userList(String userName, Integer currentPage, Integer pageSize) {
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;
        ApiUserListResponse apiUserListResponse = userService.userList(userName, currentPage, pageSize);
        return ResponseEntity.ok().body(apiUserListResponse);
    }

}
