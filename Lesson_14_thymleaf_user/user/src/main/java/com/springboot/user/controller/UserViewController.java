package com.springboot.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.user.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserViewController {
    private final UserService userService;

    @GetMapping(value = "/")
    public String getUserPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping(value = "/create")
    public String getCreatePage() {
        return "create";
    }

    @GetMapping(value = "/detail/{id}")
    public String getDetailPage(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("images", userService.getFiles(id));

        return "detail";
    }
}
