package com.my_jobhunt.job_hunt.repository;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import com.my_jobhunt.job_hunt.model.Employer;

import org.springframework.stereotype.Repository;

@Repository
public class EmployerReppository {
    private ConcurrentHashMap<String, Employer> employers = new ConcurrentHashMap<>();

    public EmployerReppository() {

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
