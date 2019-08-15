package com.jq.spb4.mapper;

import com.jq.spb4.pojo.User;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/8/15 23:08
 */
public interface UserMapper {

//    @Select(value = "select * from user where id = #{id}")
     User get(int id);

    int insert(User user);

    int del(int id);
}
