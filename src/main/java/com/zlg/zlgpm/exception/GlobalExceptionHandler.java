package com.zlg.zlgpm.exception;

import com.zlg.zlgpm.exception.BizException;
import com.zlg.zlgpm.exception.ErrResp;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author linyouru
 */
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler implements MessageSourceAware {

    private MessageSource messageSource;

    /**
     * 用户登录后访问自身无授权的资源时shiro会抛出AuthorizationException
     * 并不会走shiroFilterFactoryBean.setUnauthorizedUrl设置的接口,所以在这捕获处理该异常
     */
    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseEntity<String> handleAuthorizationException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("无操作权限");
    }

    /**
     * 处理 json 请求体调用接口校验失败抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrResp> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {
        final String path = getPath(request);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        String code = getCode(httpStatus,"");
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
        final ErrResp resp = ErrResp.builder()
                .timestamp(new Date())
                .status(httpStatus.value())
                .code(code)
                .error(httpStatus.getReasonPhrase())
                .path(path)
                .message(errors)
                .build();
        return new ResponseEntity<>(resp, httpStatus);
    }

    /**
     * BizException异常处理
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(BizException.class)
    public ResponseEntity<ErrResp> handleBizException(BizException ex, WebRequest request) {
        final String path = getPath(request);
        final String message = getMessage(ex);
        final HttpStatus httpStatus = ex.getHttpStatus();
        final String code = getCode(httpStatus, ex.getBizCode());
        final ErrResp resp = ErrResp.builder()
                .timestamp(new Date())
                .status(httpStatus.value())
                .code(code)
                .error(httpStatus.getReasonPhrase())
                .path(path)
                .message(message).build();
//        logDetails(path, message, httpStatus, code);
        return new ResponseEntity<>(resp, httpStatus);
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private String getMessage(BizException ex) {
        if (!StringUtils.hasText(ex.getBizCode())) {
            return ex.getHttpStatus().getReasonPhrase();
        }
        if (this.messageSource == null) {
            return ex.getBizCode();
        }
        return this.messageSource.getMessage(ex.getBizCode(), ex.getArgs(), ex.getBizCode(), LocaleContextHolder.getLocale());
    }

    private String getPath(WebRequest request) {
        if (request instanceof ServletWebRequest) {
            return ((ServletWebRequest) request).getRequest().getRequestURI();
        }
        return request.getContextPath();
    }

    private String getCode(HttpStatus status, String bizCode) {
        if (!StringUtils.hasText(bizCode)) {
            return "http." + status.value();
        }
        return bizCode;
    }
}
