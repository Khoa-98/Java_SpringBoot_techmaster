package vn.techmaster.demosercurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping(value = "/admin/blog")
    public String getBlogPage(){
        return "blog";
    }

    @GetMapping(value = "/admin/users")
    public String getUserPage(){
        return "user";
    }
}
