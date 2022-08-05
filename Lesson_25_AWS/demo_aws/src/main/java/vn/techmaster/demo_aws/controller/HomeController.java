package vn.techmaster.demo_aws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String HomePage() {
        return "index";
    }
}
