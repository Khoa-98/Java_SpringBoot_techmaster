package vn.techmaster.hellojpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.hellojpa.model.Job;
import vn.techmaster.hellojpa.repository.JobRepo;

@RestController
@RequestMapping("/api/v1/job")

public class JobController {
    @Autowired
    private JobRepo jobRepo;

    @GetMapping
    public List<Job> getALlJob() {
        return jobRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Job> getJobById(@PathVariable Long id) {
        return jobRepo.findById(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteJobById(@PathVariable Long id) {
        jobRepo.deleteById(id);
    }
}
