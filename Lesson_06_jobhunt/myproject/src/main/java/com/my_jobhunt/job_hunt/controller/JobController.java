package com.my_jobhunt.job_hunt.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.my_jobhunt.job_hunt.model.Job;
import com.my_jobhunt.job_hunt.repository.EmployerRepository;
import com.my_jobhunt.job_hunt.repository.JobRepository;
import com.my_jobhunt.job_hunt.request.JobRequest;

@Controller
@RequestMapping(value = "/job")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerReppository;

    @GetMapping
    public String getListJob(Model model, String keyword) {
        model.addAttribute("employerRepository", employerReppository);
        if (keyword != null) {
            model.addAttribute("jobs", jobRepository.findByKeyword(keyword));
        } else {
            model.addAttribute("jobs", jobRepository.getALl());
        }
        return "jobs";
    }

    @GetMapping(value = "/{id}")
    public String getJobById(Model model, @PathVariable String id) {
        model.addAttribute("jobs", jobRepository.findJobById(id));
        return "job_detail";
    }

    // add job
    @GetMapping(value = "/add")
    public String addJobForm(Model model) {
        model.addAttribute("jobs", new Job());
        return "job_add";
    }

    @PostMapping("/add")
    public String addNewJob(@ModelAttribute JobRequest request) {
        // Lưu vào cơ sở dữ liệu
        jobRepository.add(Job.builder()
                .emp_id(request.emp_id())
                .title(request.title())
                .description(request.description())
                .city(request.city())
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build());
        return "redirect:/job";
    }

    // update cong viec
    @GetMapping(value = "/update/{id}")
    public String updateJobForm(Model model, @PathVariable String id) {
        model.addAttribute("jobs", jobRepository.findJobById(id));

        return "job_update";
    }

    @PostMapping("/update/{id}")
    public String updateJob(@PathVariable String id, @ModelAttribute JobRequest request) {
        LocalDateTime preUpdateTime = jobRepository.findJobById(id).getCreated_at();

        Job job = jobRepository.findJobById(id);
        job.setId(id);
        job.setTitle(request.title());
        job.setEmp_id(request.emp_id());
        job.setDescription(request.description());
        job.setCity(request.city());
        job.setCreated_at(preUpdateTime);
        jobRepository.updateJob(job);
        return "redirect:/job";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteJobById(@PathVariable String id) {
        jobRepository.deleteJob(id);
        return "redirect:/job";
    }
}
