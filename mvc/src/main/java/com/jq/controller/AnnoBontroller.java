package com.jq.controller;

import com.jq.custom.annotation.ParamMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-25 14:33
 */
@Controller
@ResponseBody
@RequestMapping("annotation")
public class AnnoBontroller {


    //标注返回状态码
    @GetMapping(value = "get/{name}-{id}")
    @ResponseStatus(HttpStatus.FOUND)//302
    public String t1(@PathVariable Long id, @PathVariable String name) {
        System.out.println(id+"---"+name);
        return "get"+id;
    }

    //使用ant风格
    @GetMapping("put/?/{*}/**/{id}")
    public String put(@PathVariable(required = false) String name, @PathVariable Long id) {
        System.out.println(id);
        return "put"+id;
    }

    //使用正则匹配
    //consumes 请求的类型即request content_type的值
    //consumes 请求的结果类型即request accept的值
    @GetMapping(value = "get/{name:[a-z-]+}{id:[0-9]+}",consumes = {"application/json"},produces = {"application/json"})
    public String regex(@PathVariable String name,@PathVariable Long id) {
        return name+id;
    }


    @GetMapping(value = "/headAndParam",headers = {"accept=application/xml"},params = {"name=jq","age!=10"})
    public String compsite() {
        return "ok";
    }
    @ParamMapping("/ownAnn")
    public String own() {
        return "ok";
    }

    public String byConfig() {
        return "config";
    }
}
