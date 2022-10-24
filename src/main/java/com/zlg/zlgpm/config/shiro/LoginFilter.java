package com.zlg.zlgpm.config.shiro;

import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class LoginFilter extends UserFilter {

    /**
     * 用户未登录拦截
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print("未登录");
    }
}
