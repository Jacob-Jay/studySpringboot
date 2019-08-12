package com.jq.spb.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-09 15:50
 */
//@ConfigurationProperties("yaml-person")
@ConfigurationProperties("person")
@Component
//@PropertySource(value = "classpath:person.properties")
public class YamlPerson {
//    @Value("${person.name}")
    private String name;
//    @Value("#{2*11}")
    private int age;
//    @Value("${person.sex}")
    private String sex;

//    @Value("$ {random.int[1024,65536]}")
    private int num2;


//    @Value("$ {random.int(10)}")
    private int num;

    public void setNum(int num) {
        this.num = num;
    }
    public void setNum2(int num2) {
        this.num2 = num2;
    }
    public void setName(String name) {
        this.name = name;
    }


    public void setAge(int age) {
        this.age = age;
    }

    /*

     public int getAge() {
        return age;
    }

     public String getName() {
        return name;
    }
    public String getSex() {
        return sex;
    }*/

    public void setSex(String sex) {
        this.sex = sex;
    }
}
