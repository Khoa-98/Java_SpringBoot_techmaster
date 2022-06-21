package vn.techmaster.login;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import vn.techmaster.login.exception.UserException;
import vn.techmaster.login.model.User;
import vn.techmaster.login.service.UserService;

@SpringBootTest
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void addUser() {
        User user = userService
                .addUser("Tran dang khoa", "anhkhoa98@gmail.com", "12345@");
        assertThat(user).isNotNull();
    }

    @Test
    public void login() {
        userService
                .addUser("Tran dang khoa", "anhkhoa98@gmail.com", "12345@");
        /*
         * assertThat(userService.login("anhkhoa98@gmail.com", "12345@")).isNotNull();
         * assertThat(userService.login("anhkhoa98@gmail.com", "12345@+")).isNull();
         */
    }

    @Test
    public void login_account_when_pending() {
        userService
                .addUser("Tran dang khoa", "anhkhoa98@gmail.com", "12345@");
        assertThatThrownBy(() -> {
            userService.login("anhkhoa98@gmail.com", "12345@");
        }).isInstanceOf(UserException.class).hasMessageContaining("User is not Activated");
    }

    @Test
    public void login_account_when_not_found() {
        userService
                .addUser("Tran dang khoa", "anhkhoa98@gmail.com", "12345@");
        assertThatThrownBy(() -> {
            userService.login("anhkhoa981111@gmail.com", "12345@");
        }).isInstanceOf(UserException.class).hasMessageContaining("User is not found");
    }

    @Test
    public void login_account_when_password_incorrect() {
        userService
                .addActiveUser("Tran dang khoa", "anhkhoa98@gmail.com", "12345@");
        assertThatThrownBy(() -> {
            userService.login("anhkhoa98@gmail.com", "12345-");
        }).isInstanceOf(UserException.class).hasMessageContaining("Password is not true");
    }

    @Test
    public void login_success() {
        userService
                .addActiveUser("Tran dang khoa", "anhkhoa98@gmail.com", "12345@");
       User user =  userService.login("anhkhoa98@gmail.com", "12345@");
       assertThat(user).isNotNull();
    }
}
