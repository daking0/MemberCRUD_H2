package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Home 화면 띄우기 위한 Home Controller
@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
