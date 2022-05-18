package com.spring_restapi.btvn_02.HomeController;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.spring_restapi.btvn_02.dto.JobRequest;
import com.spring_restapi.btvn_02.model.Job;
import com.spring_restapi.btvn_02.model.Location;

@RestController
@RequestMapping("/job")
public class Controller {
    private ConcurrentHashMap<String, Job> jobs;

    public Controller() {
        jobs = new ConcurrentHashMap<>();
        jobs.put("0X-11", new Job("0X-11", "Java-backend", "thiet ke he thong", Location.HANOI, 5000000, 10000000,
                "dangkhoa@gmail.com"));
        jobs.put("0X-12", new Job("0X-12", "Frontend", "thiet ke he thong", Location.HAIPHONG, 6000000, 15000000,
                "dangkhoa@gmail.com"));
        jobs.put("0X-13", new Job("0X-13", "full-stack", "thiet ke he thong", Location.DANANG, 7000000, 20000000,
                "dangkhoa@gmail.com"));
    }

    @GetMapping
    public List<Job> getJob() {
        return jobs.values().stream().toList();
    }

    @PostMapping
    public Job createJob(@RequestBody JobRequest jobRequest) {
        String uuid = UUID.randomUUID().toString();
        Job newJob = new Job(uuid, jobRequest.title(), jobRequest.description(), jobRequest.location(),
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email_to());
        jobs.put(uuid, newJob);
        return newJob;
    }

    @PutMapping(value = "/{id}")
    public Job updateJobById(@RequestParam String id, @RequestBody JobRequest jobRequest) {
        Job updateJob = new Job(id, jobRequest.title(), jobRequest.description(), jobRequest.location(),
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email_to());
        jobs.put(id, updateJob);
        return updateJob;
    }

    @DeleteMapping(value = "/{id}")
    public Job deleteJobById(@RequestParam String id) {
        Job removeJob = jobs.remove(id);
        return removeJob;
    }

    @GetMapping(value = "/sortjobbylocation")
    public List<Job> sortJobByLocation() {
        return jobs.values().stream()
                .sorted(Comparator.comparing(j -> j.getLocation().getValue()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/salary/{salary}")
    public List<Job> getJobBysalary(@PathVariable("salary") Integer salary) {
        return jobs.values().stream()
                .filter(j -> j.getMin_salary() <= salary && j.getMax_salary() >= salary)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/keyword/{keyword}")
    public List<Job> getJobByKeyword(@PathVariable("keyword") String keyword) {
        return jobs.values().stream()
                .filter(j -> j.getTitle().toLowerCase().contains(keyword)
                        || j.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "query")
    public List<Job> getJobByquery(@RequestParam("keyword") String keyword,
            @RequestParam("location") Location location) {
        return jobs.values().stream()
                .filter(j -> (j.getTitle().toLowerCase().contains(keyword)
                        || j.getDescription().toLowerCase().contains(
                                keyword))
                        && j.getLocation().equals(location))
                .collect(Collectors.toList());
    }

}
