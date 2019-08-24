package com.jq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-16 21:57
 */
@Controller
@RequestMapping("hello")
public class HelloController {

    @ResponseBody
    @RequestMapping()
    public String hello(HttpServletRequest request) {
        return "hello";
    }
}
