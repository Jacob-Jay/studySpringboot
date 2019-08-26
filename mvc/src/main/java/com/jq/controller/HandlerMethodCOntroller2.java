package com.jq.controller;

import com.jq.dao.RequestFormData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-25 22:22
 */
@Controller
@RequestMapping("handlerMethod2")
public class HandlerMethodCOntroller2 {

    @RequestMapping("session")
    @ResponseBody
    public String session(HttpSession session, @SessionAttribute("formData") RequestFormData data) {
        return "ok";
    }
    @RequestMapping("request")
    @ResponseBody
    public String request(HttpSession session, @RequestAttribute RequestFormData data) {
        return "ok";
    }


    @RequestMapping("redirect")

    public String redirec(RequestFormData data, Model model, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("qq", "asd");
        redirectAttributes.addFlashAttribute("se", "sda");
        model.addAttribute("name", "asdasd");
            return "redirect:/handlerMethod2/redirect2";

    }
    @RequestMapping("redirect2")
    @ResponseBody
    public String redirect2(Model model,@RequestParam(required = false) String se,@RequestParam(required = false) String qq
    ,HttpSession session) {

        return "ok";

    }



    @RequestMapping("redirectForFlash")
    public String redirectForFlash(RequestFormData data, Model model, FlashMap map) {

        map.put("name2", "sda");

        model.addAttribute("name", "asdasd");
        return "redirect:/handlerMethod2/redirect2";

    }
    @RequestMapping("redirectForFlash2")
    @ResponseBody
    public String redirectForFlash2(Model model,@RequestParam(required = false) String se,@RequestParam(required = false) String qq
            ,HttpSession session) {
        return "ok";
    }


    @RequestMapping("data")
    @ResponseBody
    public RequestFormData data() {
        return new RequestFormData("asdas",20,new Date());
    }
    @RequestMapping("getData")
    @ResponseBody
    public RequestFormData getData(@RequestBody RequestFormData data) {
        return new RequestFormData("asdas",20,new Date());
    }

    @RequestMapping("httpEntity")
    @ResponseBody
    public RequestFormData httpEntity(HttpEntity<RequestFormData> entity) {
        return new RequestFormData("asdas",20,new Date());
    }

    @GetMapping("/something")
    public ResponseEntity<String> handle() {
        System.out.println(1/0);
        return new ResponseEntity<>("asd", HttpStatus.ACCEPTED);
    }

    @ExceptionHandler
    @ResponseBody
    public String excepHandler(Exception e) {
        return "ok";
    }
   /* @ExceptionHandler
    @ResponseBody
    public String excepHandler2(Exception e) {
        return "ok";
    }*/

   @RequestMapping("chinese")
    @ResponseBody
    public String chinese() {
       return "a撒打算";
    }
}
