package vn.techmaster.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import vn.techmaster.login.service.UserService;

@Component
public class ApplicationStarterRunner implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.addActiveUser("Tran dang khoa", "anhkhoa98nd@gmail.com", "123456");
        userService.addUser("John", "anhkhoa98@gmail.com", "123456789");
    }

}
