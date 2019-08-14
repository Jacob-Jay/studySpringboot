package com.jq.spb2.controller;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-14 16:29
 */
@Component
public class ErrorDealController implements ErrorController{
    @Override
    public String getErrorPath() {
        return "deFaultError";
    }
}
