package com.jq.spb2.mvc;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/8/13 23:04
 */
@Configuration
public class MvcConfig {

    @Bean
    public HttpMessageConverters customConverters(){
        HttpMessageConverter<?> additional =new StringHttpMessageConverter();
        HttpMessageConverter<?> another = new FormHttpMessageConverter();
        return new HttpMessageConverters(additional, another);
    }
}
