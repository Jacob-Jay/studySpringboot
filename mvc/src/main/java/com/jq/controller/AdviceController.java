package com.jq.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-30 16:51
 */
@ControllerAdvice
public class AdviceController {

    @InitBinder
    public void init(WebDataBinder dataBinder) {

    }

    @ExceptionHandler
    public void sout(Exception e) {

    }
}
