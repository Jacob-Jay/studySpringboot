package com.jq.spb2.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-13 20:09
 */
/*@Component
@Profile(value = {"redisPro","redisDev"})*/
public class RedisProperties {

    private String name;

    public RedisProperties(String name) {
        this.name = name;
    }

    public RedisProperties() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
