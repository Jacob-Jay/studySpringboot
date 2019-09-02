package com.jq.custom.view;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-09-02 17:15
 */
public class OwnHanlderMapping implements HandlerMapping,Ordered {
    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        HandlerExecutionChain chain = new HandlerExecutionChain(null);
        System.out.println("own....");
        return chain;
    }

    @Override
    public int getOrder() {
        return  -111;
    }
}
