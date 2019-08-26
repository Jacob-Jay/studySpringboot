package com.jq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-25 9:05
 */
@Controller
@RequestMapping("filter")
public class MethodFilterController {

    @RequestMapping()
    public String index() {
        return "MethodPage";
    }

    @PutMapping("submit")
    @ResponseBody
    public String put(String name) {
        return "put";
    }

    @PostMapping("submit")
    @ResponseBody
    public String psot(String name) {
        return "post";
    }
    @GetMapping("submit")
    @ResponseBody
    public String get(String name) {
        return "get";
    }
    @DeleteMapping("submit")
    @ResponseBody
    public String delete(String name) {
        return "delete";
    }
}
