package com.jq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
@SessionAttributes("name")
public class HelloController {


    @ModelAttribute("name")
    public String getName() {
        return "jq";
    }

    @ResponseBody
    @RequestMapping()
    public String hello(HttpServletRequest request, Model model, @SessionAttribute(name = "name",required = false)String name,@ModelAttribute("name")String name2) {
        model.addAttribute("name", "sadasdasd");
        return "hello";
    }
}
