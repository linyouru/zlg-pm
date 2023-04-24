package com.zlg.zlgpm.controller;


import com.zlg.zlgpm.controller.model.*;
import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.pojo.bo.UserMessageBo;
import com.zlg.zlgpm.pojo.po.UserPo;
import com.zlg.zlgpm.service.UserService;
import io.swagger.annotations.Api;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    public ResponseEntity<List<ApiUserMessageResponse>> getUserMessage() {
        List<ApiUserMessageResponse> userMessage = userService.getUserMessage();
        return ResponseEntity.ok().body(userMessage);
    }

    @Override
//    @RequiresRoles(value = "root")
    public ResponseEntity<ApiUserLoginResponse> updateUser(Integer id, ApiUpdateUserRequest body) {
        UserPo currentUser = (UserPo) SecurityUtils.getSubject().getPrincipal();
        if (currentUser.getId() != 1 && currentUser.getId() != id.longValue()) {
            throw new BizException(HttpStatus.UNAUTHORIZED, "user.10006");
        }
        ApiUserLoginResponse apiUserLoginResponse = userService.updateUser(id, body);
        return ResponseEntity.ok().body(apiUserLoginResponse);
    }

    @Override
    public ResponseEntity<ApiBaseResp> updateUserPassword(Integer id, String newPassword, String oldPassword) {
        userService.updatePassword(id, newPassword, oldPassword);
        return ResponseEntity.ok(new ApiBaseResp().message("success"));
    }

    @Override
    public ResponseEntity<ApiUserListResponse> userList(String userName, Integer currentPage, Integer pageSize, String startTime, String endTime) {
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;
        ApiUserListResponse apiUserListResponse = userService.userList(userName, currentPage, pageSize, startTime, endTime);
        return ResponseEntity.ok().body(apiUserListResponse);
    }

    @Override
    public ResponseEntity<ApiUserListByPidResponse> userListByPid(Integer pid, String projectName, Integer currentPage, Integer pageSize) {
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;
        projectName = null != pid ? null : projectName;
        ApiUserListByPidResponse apiUserListByPidResponse = userService.userListByPid(pid, projectName, currentPage, pageSize);
        return ResponseEntity.ok().body(apiUserListByPidResponse);
    }

    @Override
    public ResponseEntity<ApiUserListByPidResponse> userListByProjectName(String projectName, Integer currentPage, Integer pageSize) {
        currentPage = currentPage == null ? 1 : currentPage;
        pageSize = pageSize == null ? 10 : pageSize;
        ApiUserListByPidResponse apiUserListByPidResponse = userService.userListByProjectName(projectName, currentPage, pageSize);
        return ResponseEntity.ok().body(apiUserListByPidResponse);
    }

}
