package vn.techmaster.demo_jpa_03.userRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.techmaster.demo_jpa_03.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);

    List<User> findByNameIgnoreCase(String name);


    List<User> findByEmailStartsWithIgnoreCase(String email);

    long countByNameIgnoreCase(String name);



}