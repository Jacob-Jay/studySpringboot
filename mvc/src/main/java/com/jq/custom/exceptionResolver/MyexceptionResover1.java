package com.jq.custom.exceptionResolver;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-23 22:02
 */
@Order
public class MyexceptionResover1 implements HandlerExceptionResolver,Ordered {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setStatus(201);
        return new ModelAndView("ownHandler");
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
