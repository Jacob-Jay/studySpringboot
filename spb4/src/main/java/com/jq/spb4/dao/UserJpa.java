package com.jq.spb4.dao;

import com.jq.spb4.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-14 17:00
 */
@Repository
public interface UserJpa extends JpaRepository<User,Integer> {
}
