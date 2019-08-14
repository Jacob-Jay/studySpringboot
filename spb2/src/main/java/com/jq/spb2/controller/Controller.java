package com.jq.spb2.controller;

import com.jq.spb2.profile.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-13 20:13
 */
@RequestMapping("test")
@org.springframework.stereotype.Controller
public class Controller {

    @Autowired(required = false)
    private RedisProperties redisProperties;

    @RequestMapping()
    @ResponseBody
    public String test() {
        return "ok";
    }
}
