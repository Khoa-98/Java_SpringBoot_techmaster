package com.my_jobhunt.job_hunt.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import com.my_jobhunt.job_hunt.model.City;
import com.my_jobhunt.job_hunt.model.Job;

@Repository
public class JobRepository {
    private ConcurrentHashMap<String, Job> jobs = new ConcurrentHashMap<>();

    public Collection<Job> getALl() {
        return jobs.values();
    }

    // Cần sinh UUID ở đây
    public Job add(Job job) {
        String id = UUID.randomUUID().toString();
        job.setId(id);
        jobs.put(id, job);
        return job;
    }

    public Job findJobById(String id) {
        return jobs.get(id);
    }

    public Job deleteJob(String id) {
        return jobs.remove(id);
    }

    public void updateJob(Job job) {
        jobs.put(job.getId(), job);
    }

    public List<Job> findByKeyword(String keyword) {
        List<Job> jobList = jobs.values().stream()
                .filter(j -> j.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        || j.getCity().label.toLowerCase().contains(keyword
                                .toLowerCase())
                        || j.getEmp_id().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        return jobList;
    }

    @PostConstruct
    public void addSomeData() {
        this.add(Job.builder().title("Java deverloper")
                .emp_id("fpt")
                .description("Có kĩ năng tự học hỏi các công nghệ mới nhanh,....")
                .city(City.HaNoi)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build());
        this.add(Job.builder().title("PHP deverloper")
                .emp_id("cmc")
                .description("Có kĩ năng tự học hỏi các công nghệ mới nhanh,....")
                .city(City.HoChiMinh)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build());
        this.add(Job.builder().title("Fullstack deverloper")
                .emp_id("Viettel")
                .description("Có kĩ năng tự học hỏi các công nghệ mới nhanh,....")
                .city(City.HaiPhong)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build());

    }
}
