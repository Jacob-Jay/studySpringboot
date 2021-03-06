package com.jq.spb4.controller;

import com.jq.spb4.mapper.UserMapper;
import com.jq.spb4.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-14 17:00
 */
@RequestMapping("mybatis")
//@Controller
public class MybatisController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("{id}")
    @ResponseBody
    public User get(@PathVariable("id") int id) {

        return userMapper.get(id);
    }

    @RequestMapping("del/{id}")
    @ResponseBody
    public int del(@PathVariable("id") int id) {

        return userMapper.del(id);
    }


    @RequestMapping("add")
    @ResponseBody
    public int add(User user) {
        return userMapper.insert(user);
    }

}
