package com.jq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-24 22:13
 */
@Controller
@RequestMapping("multi")
public class MultiController {

    @RequestMapping
    public String index() {
        return "multi";
    }

    @RequestMapping("upload")
    @ResponseBody
    public String upload(@RequestParam("file1")MultipartFile file1,
                         @RequestParam("file2")MultipartFile file2) {

        File dir = new File("D:\\test\\file");
        String name = file1.getOriginalFilename();
        String name2 = file2.getOriginalFilename();
        try {
            file1.transferTo(new File(dir+File.separator+name));
            file2.transferTo(new File(dir+File.separator+name2));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "ok";
    }
}
