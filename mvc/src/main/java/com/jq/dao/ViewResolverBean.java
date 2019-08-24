package com.jq.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-24 11:01
 */
@XmlRootElement(name = "view")
public class ViewResolverBean {
    private String name;
    private int age;
    private List<String> likes;


    public ViewResolverBean() {
        name = "jq";
        age = 10;
        likes = new ArrayList<>();
        likes.add("like1");
        likes.add("like2");
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }
    @XmlElement
    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getLikes() {
        return likes;
    }
    @XmlElement
    public void setLikes(List<String> likes) {
        this.likes = likes;
    }
}
