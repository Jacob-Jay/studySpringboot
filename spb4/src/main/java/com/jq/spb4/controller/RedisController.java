package com.jq.spb4.controller;

import com.jq.spb4.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-16 11:29
 */
@Controller
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("{id}")
    @ResponseBody
    public String get(@PathVariable("id") String id) {
        Object o = redisTemplate.opsForValue().get(id);
        return (String) o;
    }

    @RequestMapping("add")
    @ResponseBody
    public User add(User user) {
        redisTemplate.opsForValue().set("10", user.toString());
        return user;
    }
    @RequestMapping("update")
    @ResponseBody
    public String update(User user) {
        redisTemplate.opsForValue().set("10", user.toString());
        return "ok";
    }
    @RequestMapping("del/{id}")
    @ResponseBody
    public String del(@PathVariable("id") String id) {
        redisTemplate.delete(id);
        return "ok";
    }
}
