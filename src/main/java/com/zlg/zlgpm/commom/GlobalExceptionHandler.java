package com.zlg.zlgpm.commom;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 捕获shiro AuthorizationException异常
 * @author linyouru
 */
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseEntity<String> handleAuthorizationException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("无操作权限");
    }
}
