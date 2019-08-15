package com.jq.spb4.controller;

import com.jq.spb4.dao.UserJpa;
import com.jq.spb4.mapper.UserMapper;
import com.jq.spb4.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-14 17:00
 */
@RequestMapping("jpa")
@Controller
public class JpaController {

    @Autowired
    private UserJpa  userJpa;

    @RequestMapping("{id}")
    @ResponseBody
    public User get(@PathVariable("id") int id) {

        Optional<User> byId = userJpa.findById(id);
        User user = byId.get();
        return user;
    }

    @RequestMapping("del/{id}")
    @ResponseBody
    public int del(@PathVariable("id") int id) {

         userJpa.deleteById(id);
         return 1;
    }


    @RequestMapping("add")
    @ResponseBody
    public User add(User user) {

        User save = userJpa.save(user);
        return save;
    }

}
