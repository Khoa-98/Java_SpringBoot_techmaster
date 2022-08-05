package com.my_jobhunt.job_hunt.repository;


import com.my_jobhunt.job_hunt.model.Employer;

import org.springframework.data.jpa.repository.JpaRepository;



public interface EmployerRepository extends JpaRepository<Employer, String>{
    
    
}
