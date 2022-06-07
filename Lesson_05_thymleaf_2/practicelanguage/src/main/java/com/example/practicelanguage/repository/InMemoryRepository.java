package com.example.practicelanguage.repository;

import java.util.HashMap;
import java.util.List;

import com.example.practicelanguage.Model.Job;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryRepository {
        private HashMap<String, List<Job>> jobs;

        public InMemoryRepository() {
                jobs = new HashMap<>();
                jobs.put("en", List.of(
                                new Job("en", "doctor", "doctor"),
                                new Job("en", "engineer", "engineer"),
                                new Job("en", "staff", "staff"),
                                new Job("en", "teacher", "teacher")));

                jobs.put("vn", List.of(
                                new Job("vn", "doctor", "bác sỹ"),
                                new Job("vn", "engineer", "kỹ sư"),
                                new Job("vn", "staff", "nhân viên"),
                                new Job("vn", "teacher", "giáo viên")));

                jobs.put("it", List.of(
                                new Job("it", "doctor", "medico"),
                                new Job("it", "engineer", "ingegnere"),
                                new Job("it", "staff", "personale"),
                                new Job("it", "teacher", "insegnante")));
        }

        public Object getJobByLang2(String lang) {
            return jobs.get(lang);
        }     
}