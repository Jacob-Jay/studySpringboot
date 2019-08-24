package com.jq.controller;

import com.jq.dao.TimeData;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-21 18:32
 */
@Controller
@RequestMapping("initBinder")
public class InitBinderController {

    @RequestMapping()
    public String index() {
        System.out.println(1/0);
        return "initbinder";
    }


    @InitBinder
    public 	void InitBinder(WebDataBinder binder){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class,dateEditor);
    }

    @InitBinder("stu")
    public void init2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("s.");
    }

    @RequestMapping("testDate")
    @ResponseBody
    public String ok(TimeData date,TimeData stu, @ModelAttribute("mol")TimeData mol) {
        System.out.println(date.toString());
        return "ok";
    }
}
