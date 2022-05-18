package com.spring_boot.onlab_03.controller;

import com.spring_boot.onlab_03.model.Book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String homePage(Model model) {
        Book book = new Book("de men phieu luu ki", "to hoai");
        model.addAttribute("book", book);
        model.addAttribute("name", "khoa");
        return "index";
    }

    @GetMapping(value = "/text")
    public String textPage(Model model) {

        return "text";
    }
    
    @GetMapping(value = "/about")
    public String displayAbout(Model model, @RequestParam("foo") String a, @RequestParam("tom") String b) {
        model.addAttribute("foo", a);
        model.addAttribute("tom", b);
        return "about";
    }


    @GetMapping(value = "/json", produces = "application/json")
    @ResponseBody
    public Book returnBook() {
        return new Book("de men phieu luu ki", "to hoai");
    }
    
}
