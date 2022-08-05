package vn.techmaster.hellojpa.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.techmaster.hellojpa.model.Job;

public interface JobRepo extends JpaRepository<Job, Long> {

}
