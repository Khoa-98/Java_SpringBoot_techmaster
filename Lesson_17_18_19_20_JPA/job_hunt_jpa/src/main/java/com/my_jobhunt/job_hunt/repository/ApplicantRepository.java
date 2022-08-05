package com.my_jobhunt.job_hunt.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.my_jobhunt.job_hunt.model.Applicant;
import com.my_jobhunt.job_hunt.model.Skill;

@Repository
public class ApplicantRepository {
    ConcurrentHashMap<String, Applicant> applicants;

    public ApplicantRepository() {
        applicants = new ConcurrentHashMap<>();
        applicants.put("1", new Applicant("1", "Java deverloper", "fpt", "Nguyễn Văn A", "nva@mail.com", "0923456789",
                List.of(Skill.CSharp, Skill.SQL)));
        applicants.put("2", new Applicant("2", "PHP deverloper", "cmc", "Nguyễn Thị B", "ntb@mail.com", "0923452129",
                List.of(Skill.Java, Skill.CSharp)));
        applicants.put("3",
                new Applicant("3", "fullstack deverloper", "Viettel", "Nguyễn Văn C", "nvc@mail.com", "0923167892",
                        List.of(Skill.CSharp, Skill.SQL, Skill.AWS)));
        applicants.put("4", new Applicant("4", "Java deverloper", "fpt", "Nguyễn Thị D", "ntd@mail.com", "093167892",
                List.of(Skill.CSharp, Skill.SQL, Skill.AWS, Skill.Java)));
    }

    public List<Applicant> getApplicants() {
        return applicants.values().stream().toList();
    }

    public Applicant getApplicantById(String id) {
        return applicants.get(id);
    }

    public void createApplicant(Applicant applicant) {
        applicants.put(applicant.getId(), applicant);
    }

    public void updateApplicant(Applicant applicant) {
        applicants.put(applicant.getId(), applicant);
    }

    public void deleteApplicantById(String id) {
        applicants.remove(id);
    }
}
