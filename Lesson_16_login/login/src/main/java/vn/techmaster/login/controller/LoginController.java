package vn.techmaster.login.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.login.dto.UserDto;
import vn.techmaster.login.exception.UserException;
import vn.techmaster.login.model.User;
import vn.techmaster.login.request.LoginRequest;
import vn.techmaster.login.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String showHomePage(Model model, HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        if (userDto != null) {
            model.addAttribute("user", userDto);
        }
        return "index";
    }

    @GetMapping("login")
    public String showLogin(Model model) {
        model.addAttribute("loginRequest", new LoginRequest("", ""));
        return "login";
    }

    @GetMapping("admin")
    public String showAdminPage(HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        if (userDto != null) {
            return "admin";
        } else {
            return "redirect:/";
        }

    }

    @PostMapping("login")
    public String handleLogin(@Valid @ModelAttribute LoginRequest loginRequest, BindingResult result,
            HttpSession httpSession) {
        if (result.hasErrors()) {
            return "login";
        }
        User user;
        try {
            user = userService.login(loginRequest.email(), loginRequest.password());
            httpSession.setAttribute("user", new UserDto(user.getId(), user.getFullName(), user.getEmail()));
            return "redirect:/";
        } catch (UserException e) {

            switch (e.getMessage()) {
                case "User is not found":
                    result.addError(new FieldError("loginRequest", "email", "Email is not exist"));
                    break;
                case "User is not Activated":
                    result.addError(new FieldError("loginRequest", "email", "User is not Activated"));
                case "Password is not true":
                    result.addError(new FieldError("loginRequest", "password", "Password is not correct"));
            }
            return "login";
        }

    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("register")
    public String showRegister() {
        return "register";
    }

    @GetMapping("foo")
    public String foo() {
        throw new UserException("User is not found");
    }
}
