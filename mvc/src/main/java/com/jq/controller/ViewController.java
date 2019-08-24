package com.jq.controller;

import com.jq.dao.ViewResolverBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-24 9:07
 */
@Controller
@RequestMapping("view")
public class ViewController {

    @ResponseStatus(code = HttpStatus.SEE_OTHER)
    @RequestMapping("jsp")
    public String jsp() {
        return "jsp/test1";
//        return "redirect:thymeleaf";
//        return "redirect:/view/thymeleaf";
    }

    @RequestMapping("thymeleaf")
    public String thymeleaf() {
        return "index";
    }


    /**
     *
     */

    @RequestMapping("content")
    public String content(Model model) {
      /*  ModelAndView mav = new ModelAndView();
        mav.addObject();*/
        model.addAttribute("resolverBean", new ViewResolverBean());
        return "content";
    }
}
