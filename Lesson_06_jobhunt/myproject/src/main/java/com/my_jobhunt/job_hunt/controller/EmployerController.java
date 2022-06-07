package com.my_jobhunt.job_hunt.controller;

import com.my_jobhunt.job_hunt.repository.EmployerReppository;
import com.my_jobhunt.job_hunt.request.EmployerRequest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        model.addAttribute("employers", new EmployerRequest("", "", "", "", null));
        return "employer_add";
    }

    @PostMapping(value = "/add", consumes = { "multipart/form-data" })
    public String addEmployer(@Valid @ModelAttribute("employers") EmployerRequest employerRequest,
            BindingResult result,
            Model model) {
        // check lỗi Logo để trống
        if (employerRequest.logo_path().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("employers", "logo_path", "Logo is required"));
        }
        if (result.hasErrors()) {
            System.out.println("ERROR: " + result.toString());
            return "employer_add";
        }
        System.out.println(employerRequest.logo_path().getOriginalFilename());

        return "redirect:/employers";
    }
}
