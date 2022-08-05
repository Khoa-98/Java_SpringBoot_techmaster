package com.my_jobhunt.job_hunt.request;

import java.util.List;

import com.my_jobhunt.job_hunt.model.Skill;

public record ApplicantRequest(
        String id,
        String job_id,
        String emp_id,
        String name,
        String email,
        String phone,
        List<Skill> skills) {
}
