package vn.techmaster.jobhunt.repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.City;
import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.model.Job;

@Repository
public class JobRepository {
    private ConcurrentHashMap<String, Job> jobs;

    public JobRepository() {
        jobs = new ConcurrentHashMap<>();
        jobs.put("j1", new Job("j1", "0X-01", "Java BackEnd", "abc", City.DaNang, LocalDateTime.now(),
                LocalDateTime.of(2022, Month.MAY, 15, 12, 30, 00)));
        jobs.put("j2", new Job("j2", "0X-01", "Front end", "abc", City.HaNoi, LocalDateTime.now(),
                LocalDateTime.of(2022, Month.MAY, 15, 12, 30, 00)));
        jobs.put("j3", new Job("j3", "0X-02", "Java BackEnd", "abc", City.HaiPhong, LocalDateTime.now(),
                LocalDateTime.of(2022, Month.MAY, 15, 12, 30, 00)));
        jobs.put("j4", new Job("j4", "0X-03", "Java BackEnd", "abc", City.DaNang, LocalDateTime.now(),
                LocalDateTime.of(2022, Month.MAY, 15, 12, 30, 00)));
    }

    public JobRepository(ConcurrentHashMap<String, Job> jobs) {
        this.jobs = jobs;
    }

    public List<Job> getAllJob() {
        return jobs.values().stream().collect(Collectors.toList());
    }
    
    public void saveJob(Job job) {
        jobs.put(job.getId(), job);
    }

    public Job getJobByID(String id) {
        return jobs.get(id);
    }

    public void updateJob(Job employer) {
        jobs.put(employer.getId(), employer);
    }

    public void deleteEmployerById(String id) {
        jobs.remove(id);
    }


}
