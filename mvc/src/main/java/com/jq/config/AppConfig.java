package com.jq.config;

import com.jq.custom.exceptionResolver.MyexceptionResover1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-16 21:53
 */
@Configuration
@ComponentScan(value = {"com.jq.service","com.jq.dao"})
public class AppConfig {

    @Bean
    public HandlerExceptionResolver ownHandlerException() {
        MyexceptionResover1 myexceptionResover1 = new MyexceptionResover1();
        return myexceptionResover1;
    }
}
