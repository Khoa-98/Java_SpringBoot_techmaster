package com.spring_restapi.btvn_02.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.spring_restapi.btvn_02.model.Location;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Job {
    private String id;
    private String title;
    private String description;
    private Location location;
    private int min_salary;
    private int max_salary;
    private String email_to;

}
