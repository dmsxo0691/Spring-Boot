package com.example.secondproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {
    @GetMapping("hi")
    public String hi(Model model){
        model.addAttribute("username","dmsxo");
        return "greeting";
    }
    @GetMapping("bye")
    public String bye(Model model){
        model.addAttribute("nickname", "은태");
        return "goodbye";
    }
}
