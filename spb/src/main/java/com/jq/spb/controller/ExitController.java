package com.jq.spb.controller;

import com.jq.spb.bean.YamlPerson;
import com.jq.spb.springapplication.ExitCode;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-09 11:29
 */
@Controller
public class ExitController implements ApplicationContextAware{

    private ApplicationContext applicationContext;
    @Autowired
    private YamlPerson yamlPerson;


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
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
