package com.zlg.zlgpm.controller;


import com.zlg.zlgpm.controller.model.ApiBaseResp;
import com.zlg.zlgpm.controller.model.ApiLoginRequest;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "auth", description = "授权认证相关接口")
public class LoginController implements AuthApi {

    @GetMapping("/unauthorized")
    public ResponseEntity<String> unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
    }

    @Override
    public ResponseEntity<ApiBaseResp> login(Boolean rememberMe, ApiLoginRequest body) {
        String userName = body.getUserName();
        String password = body.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password, rememberMe);
        // 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResponseEntity.ok(new ApiBaseResp().message("login success"));
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return ResponseEntity.badRequest().body(new ApiBaseResp().message(e.getMessage()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiBaseResp());
        }
    }

    @Override
    public ResponseEntity<Void> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseEntity.ok().body(null);
    }
}
