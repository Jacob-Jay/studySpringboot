package com.jq.spb.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-13 11:08
 */
//@Component
//@ConfigurationProperties("app")
public class App {

    @Value("10")
    private int id;
    private String name;
    private String desc;
    private List<String> titles;

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
