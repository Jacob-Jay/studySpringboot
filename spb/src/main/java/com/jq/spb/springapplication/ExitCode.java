package com.jq.spb.springapplication;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-09 11:40
 *
 * 退出返回的code
 */
//@Component
public class ExitCode implements ExitCodeGenerator{
    public static  Integer code = 0;

    @Override
    public int getExitCode() {
        System.out.println("tuichule...");
        return 10;
    }
}
