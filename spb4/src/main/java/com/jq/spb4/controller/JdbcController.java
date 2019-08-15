package com.jq.spb4.controller;

import com.jq.spb4.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/8/15 22:26
 */
@Controller
@RequestMapping("/user")
public class JdbcController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("{id}")
    @ResponseBody
    public List<Object> getById(@PathVariable("id") int id) {
        List<Object> query = jdbcTemplate.query("select * from user where id =  "+id, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                Map<String,Object> map = new HashMap<>();
                map.put("id",resultSet.getInt("id"));
                map.put("user_name",resultSet.getString("user_name"));
                map.put("age",resultSet.getInt("age"));
                return map;
            }
        });
        return query;
    }


    @RequestMapping("/update")
    @ResponseBody
    public User update(User user) {
        jdbcTemplate.update("update user set user_name = ?,age=? where id = ?",user.getUserName(),user.getAge(),user.getId());
        return user;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public User add(User user) {
        jdbcTemplate.update("insert into user (user_name,age)values (?,?)",user.getUserName(),user.getAge());
        return user;
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String update(@PathVariable int id) {
        jdbcTemplate.update("delete from user where id = ?",id);
        return "ssd";
    }
}
