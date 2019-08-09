package com.jq.spb.springapplication;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-09 11:18
 * 启动完成后执行
 */
//@Component
@Order(10)
public class AppRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("++++++++++++++AppRunner++++++++++");
        String[] sourceArgs = args.getSourceArgs();
        for (String sourceArg : sourceArgs) {
            System.out.println(sourceArg);
        }
        System.out.println("++++++++++++++AppRunner++++++++++");
    }
}
