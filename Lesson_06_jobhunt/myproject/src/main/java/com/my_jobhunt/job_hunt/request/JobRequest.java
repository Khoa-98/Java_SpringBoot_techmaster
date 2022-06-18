package com.my_jobhunt.job_hunt.request;

import com.my_jobhunt.job_hunt.model.City;

public record JobRequest(String title, String emp_id, String description, City city) {

}
