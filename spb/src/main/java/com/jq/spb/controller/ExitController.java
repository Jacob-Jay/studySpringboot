package com.jq.spb.controller;

import com.jq.spb.bean.App;
import com.jq.spb.bean.YamlPerson;
import com.jq.spb.springapplication.ExitCode;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-09 11:29
 */
@Controller
@PropertySource(value = "classpath:controller.properties")
//@ConfigurationProperties("exti")
public class ExitController implements ApplicationContextAware{

    @Value("${exti.name}")
    private String name;

    @Value("${exti.date}")
    private Date date;

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private ApplicationContext applicationContext;
    @Autowired
    private YamlPerson yamlPerson;
    @Autowired
    private App app;


    @RequestMapping(value = "/shutdown", method = RequestMethod.GET)
    public void shutdown() {
        ExitCode.code = 10;
        //final int exitCode = ExitCode.code;
        /*ExitCodeGenerator exitCodeGenerator = new ExitCodeGenerator() {
            @Override
            public int getExitCode() {
                System.out.println("tuichu.........");
                return exitCode;
            }

        };*/
        SpringApplication.exit(applicationContext);
//        System.out.println("ok");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
