package com.jq.custom.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-23 21:09
 */
public class Myinterceptor1 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       /* response.setStatus(403);
        return false;*/
       return true;
    }
}
