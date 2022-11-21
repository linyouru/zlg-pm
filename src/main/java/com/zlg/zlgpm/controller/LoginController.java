package com.zlg.zlgpm.controller;


import com.zlg.zlgpm.commom.OperationLog;
import com.zlg.zlgpm.controller.model.ApiLoginRequest;
import com.zlg.zlgpm.controller.model.ApiLoginResponse;
import com.zlg.zlgpm.controller.model.ApiUserResponse;
import com.zlg.zlgpm.service.UserService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "auth", description = "授权认证相关接口")
public class LoginController implements AuthApi {

    @Resource
    private UserService userService;

    @GetMapping("/unauthorized")
    public ResponseEntity<String> unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
    }

    @Override
    @OperationLog(value = "登录")
    public ResponseEntity<ApiLoginResponse> login(Boolean rememberMe, ApiLoginRequest body) {
        String userName = body.getUserName();
        String password = body.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password, rememberMe);
        // 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        ApiLoginResponse response = new ApiLoginResponse();
        try {
            subject.login(token);
            ApiUserResponse apiUserResponse = userService.queryUserByName(userName);
            response.setResult(apiUserResponse);
            return ResponseEntity.ok(response);
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (AuthenticationException e) {
            response.setMessage("server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Override
    public ResponseEntity<Void> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseEntity.ok().body(null);
    }
}
