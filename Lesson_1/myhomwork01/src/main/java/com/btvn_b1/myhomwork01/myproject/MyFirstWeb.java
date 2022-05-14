package com.btvn_b1.myhomwork01.myproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class MyFirstWeb {
    @GetMapping
    @ResponseBody
    public String helloWorld() {
        return "hello world";
    }
}
