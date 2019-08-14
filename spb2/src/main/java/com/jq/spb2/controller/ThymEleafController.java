package com.jq.spb2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-14 16:19
 */
@RequestMapping("thymeleaf")
@Controller
public class ThymEleafController {


    @RequestMapping()
    public String index() {
        return "index";
    }
}
