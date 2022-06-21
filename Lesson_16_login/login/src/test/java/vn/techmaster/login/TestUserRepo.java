package vn.techmaster.login;

import org.junit.jupiter.api.Test;

import vn.techmaster.login.model.State;
import vn.techmaster.login.model.User;
import vn.techmaster.login.repository.UserRepo;
import static org.assertj.core.api.Assertions.*;

public class TestUserRepo {
    @Test
    public void addUser() {
        UserRepo userRepo = new UserRepo();
        User user = userRepo.addUser("Trần Đăng Khoa", "anhkhoa98@gmail.com", "dk0001", State.PENDING);

        assertThat(user).isNotNull();
        System.out.println("id= " + user.getId());
        assertThat(user.getId()).isNotNull();
        assertThat(user.getState()).isEqualTo(State.PENDING);
    }

    @Test
    public void isEmailExist() {
        UserRepo userRepo = new UserRepo();
        userRepo.addUser(
                "Trần Đăng Khoa", "anhkhoa98@gmail.com", "dk0001", State.PENDING);
        userRepo.addUser(
                "Trần Đăng Hưng", "anhkhoa98@gmail.com", "dk0001", State.PENDING);
        userRepo.addUser(
                "Nguyễn thị thu hương", "huong8898@gmail.com", "dk0001", State.PENDING);
        assertThat(userRepo.isEmailExist("anhkhoa98@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("anhkhoa9800000@gmail.com")).isFalse();
        assertThat(userRepo.isEmailExist("Anhkhoa98@gmail.com")).isTrue();
    }

    @Test
    public void findEmail() {
        UserRepo userRepo = new UserRepo();
        userRepo.addUser(
                "Trần Đăng Khoa", "anhkhoa98@gmail.com", "dk0001", State.PENDING);
        userRepo.addUser(
                "Trần Đăng Hưng", "anhkhoa98@gmail.com", "dk0001", State.PENDING);
        userRepo.addUser(
                "Nguyễn thị thu hương", "huong8898@gmail.com", "dk0001", State.PENDING);
        assertThat(userRepo.findEmail("anhkhoa98@gmail.com")).isPresent();
        assertThat(userRepo.findEmail("anhkhoa9811@gmail.com")).isNotPresent();
    }
}
