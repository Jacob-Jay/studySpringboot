package com.jq.spb.springapplication;

import org.springframework.boot.ApplicationArguments;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-09 11:02
 * 获取启动参数
 */
//@Component
public class ArgsBean {
   // @Autowired
    public ArgsBean(ApplicationArguments args) {
        boolean debug = args.containsOption("a");
        System.out.println("sssssssssssssssssssssssssss      "+debug);
        String[] sourceArgs = args.getSourceArgs();
        for (String sourceArg : sourceArgs) {
            System.out.println(" param2           "+sourceArg);
        }
    }
}
