package com.jq.spb2.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-13 20:19
 */
@Configuration
public class ConfigCompnent {


    @Bean
    @Profile("redisDev")
    public RedisProperties redisPropertiesDev(){
        return  new RedisProperties("dev");
    }
    @Bean
    @Profile("redisPro")
    public RedisProperties redisPropertiesPro(){
        return  new RedisProperties("redisPro");
    }
    @Bean
    @Profile("redisTest")
    public RedisProperties redisPropertiesTest(){
        return  new RedisProperties("redisTest");
    }
}
