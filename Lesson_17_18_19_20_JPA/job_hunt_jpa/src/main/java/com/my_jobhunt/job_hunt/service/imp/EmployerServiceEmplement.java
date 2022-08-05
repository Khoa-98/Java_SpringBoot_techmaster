package com.my_jobhunt.job_hunt.service.imp;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my_jobhunt.job_hunt.model.Employer;
import com.my_jobhunt.job_hunt.repository.EmployerRepository;
import com.my_jobhunt.job_hunt.service.EmployerService;

@Service
public class EmployerServiceEmplement implements EmployerService{
    @Autowired 
    private EmployerRepository employerRepository;

    // Cần sinh UUID ở đây
    public Employer add(Employer employer) {
        String id = UUID.randomUUID().toString();
        employer.setId(id);
        employerRepository.save(employer);
        return employer;
    }

    public Collection<Employer> getALl() {
        return employerRepository.findAll();
    }

    public Optional<Employer> findId(String id) {
        return employerRepository.findById(id);
    }

    // Cập nhật logo của employer
    public void updateLogo(String id, String logo_path) {
        var emp = employers.get(id);
        emp.setLogo_path(logo_path);
        employers.put(id, emp);
    }

    // Cập nhật employer:
    public void updateEmployer(Employer employer) {
        employerRepository.save(employer);
    }

    // xoa employer
    public void deleteById(String id) {
         employerRepository.deleteById(id);
    }

    // tim kiem theo keyword
    public List<Employer> findByKeyword(String keyword) {
        List<Employer> jobList = employers.values().stream()
                .filter(j -> j.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        return jobList;
    }

    // @PostConstruct
    // public void addSomeData() {
    //     this.add(Employer.builder().name("FPT")
    //             .website("https://fpt.com.vn")
    //             .logo_path("fpt.png")
    //             .email("fpt@gmail.com")
    //             .build());
    //     this.add(Employer.builder().name("cmc")
    //             .website("https://cmc.com.vn")
    //             .logo_path("cmc.jpg")
    //             .email("fpt@gmail.com")
    //             .build());
    //     this.add(Employer.builder().name("FPT")
    //             .website("https://fpt.com.vn")
    //             .logo_path("fpt.png")
    //             .email("fpt@gmail.com")
    //             .build());
    // }
}
