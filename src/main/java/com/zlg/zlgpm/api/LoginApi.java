package com.zlg.zlgpm.api;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginApi {

    @PostMapping("/login")
    public ResponseEntity<Object> login(String username, String password, Boolean rememberMe) {
        // 密码MD5加密
//        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        // 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResponseEntity.ok("login success");
        } catch (UnknownAccountException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (LockedAccountException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseEntity.ok().body("logout");
    }

    @GetMapping("/unauthorized")
    public ResponseEntity<String> unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("unauthorized");
    }
}
