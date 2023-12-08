package com.bitc.spring_proj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SPController {


    @RequestMapping("/Proj/festival/pj_main")
    public String pj_main() throws Exception {
        return "festival/pj_main";
    }

    @GetMapping("/Proj/festival/f_detail")
    public String f_detail() throws Exception {
        return "festival/f_detail";
    }
}
