package com.jq.custom.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-23 20:14
 */

/**
 * 使用注解配置需要定义拦截规则,符合规则都生效
 *
 * getFilter返回的只对dispatchServlet生效
 *
 */
//@WebFilter(urlPatterns = {"/*"})
public class Myfilter1 implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("do filter1");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
