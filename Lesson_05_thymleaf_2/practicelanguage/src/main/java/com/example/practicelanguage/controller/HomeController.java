package com.example.practicelanguage.controller;

import java.util.Locale;

import com.example.practicelanguage.repository.InMemoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private InMemoryRepository repo;

    @GetMapping("/hello")
    public String showHello(Model model) {
        Locale locale = LocaleContextHolder.getLocale();
        model.addAttribute("jobs", repo.getJobByLang2(locale.getLanguage()));
        return "hello";
    }
}
