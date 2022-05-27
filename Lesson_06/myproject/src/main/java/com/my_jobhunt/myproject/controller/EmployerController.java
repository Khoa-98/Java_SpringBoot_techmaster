package com.my_jobhunt.myproject.controller;

import com.my_jobhunt.myproject.model.Employer;
import com.my_jobhunt.myproject.repository.EmployerReppository;
import com.my_jobhunt.myproject.request.EmployerRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/employer")
public class EmployerController {
    @Autowired
    private EmployerReppository employerReppository;

    @GetMapping
    public String listAllEmployers(Model model) {
        model.addAttribute("employers", employerReppository.getALl());
        return "employers";
    }

    @GetMapping(value = "/{id}")
    public String showEmployerDetailById(Model model, @PathVariable String id) {
        model.addAttribute("employers", employerReppository.findId(id));
        return "employer_detail";
    }

    @GetMapping(value = "/add")
    public String addEmployerForm(Model model) {
        model.addAttribute("employers", new EmployerRequest("", "","","", null));
        return "employer_add";
    }

    @PostMapping(value = "/add")
    public String addEmployer() {
        return "redirect:/employers";
    }
}
