package com.jq.spb.springapplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-09 11:19
 * 启动后执行
 */
//@Component
@Order(10)
public class CommondRead implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("+++++++++++CommondRead++++++++++++");
        for (Object arg : args) {
            System.out.println(arg);
        }
        System.out.println("+++++++++++++CommondRead+++++++++++");
    }
}
