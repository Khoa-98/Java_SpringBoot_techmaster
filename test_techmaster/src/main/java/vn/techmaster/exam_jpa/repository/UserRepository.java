package vn.techmaster.exam_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.exam_jpa.entity.User;

import java.util.Iterator;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);
}