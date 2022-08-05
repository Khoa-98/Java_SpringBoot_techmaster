package vn.techmaster.user_demo_jpa_03;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import vn.techmaster.user_demo_jpa_03.dto.UserDto;
import vn.techmaster.user_demo_jpa_03.entity.User;
import vn.techmaster.user_demo_jpa_03.userRepo.UserRepository;

import java.util.List;

@SpringBootTest
class UserdemoJpa03ApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void insertUserTest() {
        User user = User.builder().name("dangkhoa").email("dangkhoa98@gmail.com")
        .build();
        User user1 = User.builder().name("nguyen huong").email("nguyenhuong02@gmail.com")
                .build();
        User user2 = User.builder().name("ba sang").email("basang02@gmail.com")
                .build();

        userRepository.save(user);
        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    void countByNameContainsIgnoreCaseTest(){
        Long count = userRepository.countByNameContainsIgnoreCase("Khoa");
        System.out.println(count);
        Assertions.assertThat(count).isEqualTo(1L);

    }

    @Test
    void findByOrderByNameDesc(){
        List<User> users = userRepository.findByOrderByNameDesc(Sort.by("name").descending());
        users.forEach(user -> System.out.println(user.getName()));
    }

    @Test
    void  findByOrderByNameAscTest(){
        List<User> users = userRepository.findByOrderByNameAsc(PageRequest.of(0, 3));
        users.forEach(user -> System.out.println(user.getName()));
    }

    @Test
    void findByEmailReturnDto(){
        UserDto userDto = userRepository.findByEmail("dangkhoa98@gmail.com");
        System.out.println(userDto.getName());
        Assertions.assertThat(userDto.getName()).isEqualTo("dangkhoa");
    }
}
