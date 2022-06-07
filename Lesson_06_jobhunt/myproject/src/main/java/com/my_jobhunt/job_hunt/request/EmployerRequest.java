package com.my_jobhunt.job_hunt.request;

import javax.validation.constraints.*;

import org.springframework.web.multipart.MultipartFile;

public record EmployerRequest(String id,
        @Size(min = 5, max = 30, message = "Name must between 5 and 30") 
        String name,
        @NotBlank(message = "Website cannot null") 
        String website,
        @NotBlank(message = "Email cannot null") @Email(message = "Not valid email") 
        String email,
        MultipartFile logo_path) {
}
