package com.jq.custom;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-09-02 17:14
 */
public class oWNHandlerAdaptor implements HandlerAdapter,Ordered{
    @Override
    public boolean supports(Object handler) {
        return true;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return new ModelAndView("index");
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return 0;
    }

    @Override
    public int getOrder() {
        return -111;
    }
}
