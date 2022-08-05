package com.my_jobhunt.job_hunt.controller;

import com.my_jobhunt.job_hunt.model.Employer;
import com.my_jobhunt.job_hunt.repository.EmployerRepository;
import com.my_jobhunt.job_hunt.request.EmployerRequest;
import com.my_jobhunt.job_hunt.service.StorageService;
import com.my_jobhunt.job_hunt.service.imp.EmployerServiceEmplement;

import java.io.IOException;

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
    private EmployerServiceEmplement employerServiceEmplement;

    @Autowired
    private StorageService storageService;

    @GetMapping
    public String listAllEmployers(Model model, String keyword) {

        if (keyword != null) {
            model.addAttribute("employers", employerServiceEmplement.findByKeyword(keyword));
        } else {
            model.addAttribute("employers", employerServiceEmplement.getALl());
        }
        return "employers";
    }

    @GetMapping(value = "/{id}")
    public String showEmployerDetailById(Model model, @PathVariable String id) {
        model.addAttribute("employers", employerServiceEmplement.findId(id));
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
        // nếu có lỗi thì trả về trình duyệt
        if (result.hasErrors()) {
            System.out.println("ERROR: " + result.toString());
            return "employer_add";
        }
        // Lưu vào cơ sở dữ liệu
        Employer emp = employerServiceEmplement.add(Employer.builder()
                .name(employerRequest.name())
                .website(employerRequest.website())
                .email(employerRequest.email())
                .build());

        // Lưu logo vào ổ cứng
        try {
            String logoFileName = storageService.saveFile(employerRequest.logo_path(), emp.getId());
            employerServiceEmplement.updateLogo(emp.getId(), logoFileName);
        } catch (IOException e) {
            // Nếu lưu file bị lỗi thì xoá bản ghi Employer
            e.printStackTrace();
        }
        return "redirect:/employer";
    }

    @GetMapping(value = "/update/{id}")
    public String updateEmployerForm(Model model, @PathVariable String id) {
        Employer employer = employerServiceEmplement.findId(id);
        model.addAttribute("employers", employer);
        return "employer_update";
    }

    @PostMapping(value = "/update/{id}", consumes = { "multipart/form-data" })
    public String updateEmployer(@Valid @ModelAttribute("employers") EmployerRequest employerRequest,
            BindingResult result,
            Model model, @PathVariable String id) {
        // check lỗi Logo để trống
        if (employerRequest.logo_path().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("employers", "logo_path", "Logo is required"));
        }
        // nếu có lỗi thì trả về trình duyệt
        if (result.hasErrors()) {
            System.out.println("ERROR: " + result.toString());
            return "employer_update";
        }
        // Lưu vào cơ sở dữ liệu
        Employer emp = employerServiceEmplement.findId(id);
        emp.setId(id);
        emp.setName(employerRequest.name());
        emp.setWebsite(employerRequest.website());
        emp.setEmail(employerRequest.email());
        // Lưu logo vào ổ cứng
        try {
            String logoFileName = storageService.saveFile(employerRequest.logo_path(), emp.getId());
            employerServiceEmplement.updateLogo(emp.getId(), logoFileName);
        } catch (IOException e) {
            // Nếu lưu file bị lỗi thì xoá bản ghi Employer
            e.printStackTrace();
        }

        employerServiceEmplement.updateEmployer(emp);
        return "redirect:/employer";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteEmployerById(@PathVariable String id) {
        Employer emp = employerServiceEmplement.deleteById(id);
        storageService.deleteFile(emp.getLogo_path());
        return "redirect:/employer";
    }
}
