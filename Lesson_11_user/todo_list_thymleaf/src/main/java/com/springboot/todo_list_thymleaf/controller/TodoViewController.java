package com.springboot.todo_list_thymleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.todo_list_thymleaf.service.TodoService;

@Controller
public class TodoViewController {

    @Autowired
    TodoService todoService;
    
    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("todos", todoService.getTodos(""));
        return "index";
    }


}
