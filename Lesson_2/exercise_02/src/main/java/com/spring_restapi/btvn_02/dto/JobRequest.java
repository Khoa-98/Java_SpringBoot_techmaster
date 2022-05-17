package com.spring_restapi.btvn_02.dto;
import com.spring_restapi.btvn_02.model.Location;

public record JobRequest(String title, String description, Location location, int min_salary,int max_salary, String email_to ) {
    
}
