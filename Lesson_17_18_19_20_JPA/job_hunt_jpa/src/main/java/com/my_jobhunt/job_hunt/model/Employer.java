package com.my_jobhunt.job_hunt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name= "employer")
public class Employer {
    @Id
    @GeneratedValue
    private String id;

    private String name;
    private String logo_path;
    private String website;
    private String email;
}
