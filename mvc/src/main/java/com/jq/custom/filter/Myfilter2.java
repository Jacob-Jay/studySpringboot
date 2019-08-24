package com.jq.custom.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-23 20:31
 */
public class Myfilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("do filter2");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
