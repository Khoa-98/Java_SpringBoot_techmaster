package vn.techmaster.jobhunt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.jobhunt.model.City;
import vn.techmaster.jobhunt.repository.JobRepository;
import vn.techmaster.jobhunt.request.JobRequest;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobRepository jobRepository;

    @GetMapping(value = "/list")
    public String getListJob(Model model) {
        model.addAttribute("job", jobRepository.getAllJob());
        return "Job_list";
    }

    @GetMapping(value = "/add")
    public String addJob(Model model) {
        model.addAttribute("job", new JobRequest(null, null, null, null, null));
        return "job_add";
    }

}
