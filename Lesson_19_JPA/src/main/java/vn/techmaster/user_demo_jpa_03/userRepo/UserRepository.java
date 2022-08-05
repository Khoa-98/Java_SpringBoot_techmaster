package vn.techmaster.user_demo_jpa_03.userRepo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.techmaster.user_demo_jpa_03.dto.UserDto;
import vn.techmaster.user_demo_jpa_03.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);

    List<User> findByNameIgnoreCase(String name);


    List<User> findByEmailStartsWithIgnoreCase(String email);

    long countByNameContainsIgnoreCase(String name);

    List<User> findByOrderByNameDesc(Sort sort);


    List<User> findByOrderByNameAsc(Pageable pageable);

    @Query(nativeQuery = true, name = "getUserInfo")
    UserDto findByEmail(String email);









}