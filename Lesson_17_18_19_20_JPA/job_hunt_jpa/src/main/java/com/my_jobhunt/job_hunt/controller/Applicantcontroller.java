package com.my_jobhunt.job_hunt.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my_jobhunt.job_hunt.model.Applicant;
import com.my_jobhunt.job_hunt.repository.ApplicantRepository;
import com.my_jobhunt.job_hunt.repository.EmployerRepository;
import com.my_jobhunt.job_hunt.repository.JobRepository;
import com.my_jobhunt.job_hunt.request.ApplicantRequest;

@Controller
@RequestMapping(value = "/applicant")
public class Applicantcontroller {
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("/list")
    public String applicantList(Model model) {
        model.addAttribute("applicants", applicantRepository.getApplicants());
        model.addAttribute("jobRepository", jobRepository);
        model.addAttribute("employerRepository", employerRepository);
        return "applicant_list";
    }

    @GetMapping("/add")
    public String addApplicant(Model model) {
        model.addAttribute("applicant", new Applicant());
        model.addAttribute("jobs", jobRepository.getALl());
        model.addAttribute("employerRepository", employerRepository);
        return "applicant_add";
    }

    @PostMapping("/add")
    public String submitAddApplicant(@ModelAttribute ApplicantRequest applicantRequest) {
        String uuid = UUID.randomUUID().toString();
        Applicant applicant = new Applicant(uuid, applicantRequest.job_id(), applicantRequest.emp_id(),
                applicantRequest.name(),
                applicantRequest.email(), applicantRequest.phone(), applicantRequest.skills());
        applicantRepository.createApplicant(applicant);
        return "redirect:/applicant/list";
    }

    @GetMapping("/update/{id}")
    public String updateApplicant(Model model, @PathVariable String id) {
        Applicant applicant = applicantRepository.getApplicantById(id);
        model.addAttribute("applicant", applicant);
        model.addAttribute("jobs", jobRepository.getALl());
        model.addAttribute("employerRepository", employerRepository);
        return "applicant_update";
    }

    @PostMapping("/update/{id}")
    public String submitUpdateApplicant(@PathVariable String id, @ModelAttribute ApplicantRequest applicantRequest) {
        Applicant applicant = new Applicant(id, applicantRequest.job_id(), applicantRequest.emp_id(),
                applicantRequest.name(),
                applicantRequest.email(), applicantRequest.phone(), applicantRequest.skills());
        applicantRepository.updateApplicant(applicant);
        return "redirect:/applicant/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteApplicant(@PathVariable String id) {
        applicantRepository.deleteApplicantById(id);
        return "redirect:/applicant/list";
    }
}
