package com.my_jobhunt.job_hunt.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.my_jobhunt.job_hunt.model.Employer;

public interface EmployerService {
    Employer add(Employer employer);

    Collection<Employer> getALl();
    
    Optional<Employer> findId(String id);

    void updateLogo(String id, String logo_path);

    void updateEmployer(Employer employer);

    void deleteById(String id);

    List<Employer> findByKeyword(String keyword);


}
