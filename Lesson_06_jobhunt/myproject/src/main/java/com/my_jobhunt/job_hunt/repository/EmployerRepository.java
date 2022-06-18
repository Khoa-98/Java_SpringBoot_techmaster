package com.my_jobhunt.job_hunt.repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.my_jobhunt.job_hunt.model.Employer;

import org.springframework.stereotype.Repository;

@Repository
public class EmployerRepository {
    private ConcurrentHashMap<String, Employer> employers = new ConcurrentHashMap<>();

    public EmployerRepository() {

    }

    // Cần sinh UUID ở đây
    public Employer add(Employer employer) {
        String id = UUID.randomUUID().toString();
        employer.setId(id);
        employers.put(id, employer);
        return employer;
    }

    public Collection<Employer> getALl() {
        return employers.values();
    }

    public Employer findId(String id) {
        return employers.get(id);
    }

    // Cập nhật logo của employer
    public void updateLogo(String id, String logo_path) {
        var emp = employers.get(id);
        emp.setLogo_path(logo_path);
        employers.put(id, emp);
    }

    // Cập nhật employer:
    public void updateEmployer(Employer employer) {
        employers.put(employer.getId(), employer);
    }

    // xoa employer
    public Employer deleteById(String id) {
        return employers.remove(id);
    }

    // tim kiem theo keyword
    public List<Employer> findByKeyword(String keyword) {
        List<Employer> jobList = employers.values().stream()
                .filter(j -> j.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        return jobList;
    }

    @PostConstruct
    public void addSomeData() {
        this.add(Employer.builder().name("FPT")
                .website("https://fpt.com.vn")
                .logo_path("fpt.png")
                .email("fpt@gmail.com")
                .build());
        this.add(Employer.builder().name("cmc")
                .website("https://cmc.com.vn")
                .logo_path("cmc.jpg")
                .email("fpt@gmail.com")
                .build());
        this.add(Employer.builder().name("FPT")
                .website("https://fpt.com.vn")
                .logo_path("fpt.png")
                .email("fpt@gmail.com")
                .build());
    }
}
