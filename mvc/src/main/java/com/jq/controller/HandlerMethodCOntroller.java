package com.jq.controller;

import com.jq.dao.RequestFormData;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-25 16:44
 */
@RestController
@RequestMapping("handlerMethod")
@SessionAttributes("formData")
public class HandlerMethodCOntroller {


    //xxxx/martrix;color=a,b,c;ids=asd/id
    @RequestMapping("{martrix}/{id}")
    public String martrix(@PathVariable String martrix, @MatrixVariable Long ids,@MatrixVariable(name = "color")String[]colors, @PathVariable Long id) {
        return "ok";
    }

    @RequestMapping("param")
    public String param(@RequestParam String name, @RequestParam Map<String,String>params) {
        return name;
    }

    @RequestMapping("head")
    public String head(@RequestHeader String accept, @RequestHeader Map<String,String>headers) {
        return accept;
    }

    //不能使用map接收多个
    @RequestMapping("cookie")
    public String cookie(@CookieValue String name) {
        return name;
    }
    //================================================================================================


    @ModelAttribute("formData")
    public RequestFormData model() {
        return new RequestFormData("qw",10,new Date());
    }
    /**
     * 1、使用在参数上从模型中获取数据，如果灭有就创建并添加
     * model获取
     * SessionAttributes获取
     * url获取
     * 构造对象并填充
     * 2、使用在方法上往模型中添加数据
     * @return ss
     */
    @RequestMapping("modelAttribute")
    public String head(HttpSession session, @ModelAttribute(name = "formData",binding = true) RequestFormData data,
                       BindingResult result, Model model, SessionStatus sessionStatus) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().toString());
        }
//        sessionStatus.setComplete();
        return "ok";
    }
}
