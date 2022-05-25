package vn.techmaster.jobhunt.controller;

import java.util.Optional;
import java.util.UUID;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.repository.EmployerRepository;
import vn.techmaster.jobhunt.request.EmployerRequest;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    private EmployerRepository repoEmployer;

    @GetMapping(value = "/list")
    public String getListEmployer(Model model) {
        model.addAttribute("employers", repoEmployer.getAllEmployers());
        return "employers";
    }

    @GetMapping(value = "/{id}")
    public String getEmployerDetailById(Model model, @PathVariable String id) {
        model.addAttribute("employers", repoEmployer.findEmployerByID(id));
        return "employerDetail";
    }

    @GetMapping(value = "/add")
    public String addEmployer(Model model) {
        model.addAttribute("employer", new EmployerRequest("", "", "", "", ""));
        return "Employer_add";
    }

    @PostMapping("/add")
    public String handleForm(@ModelAttribute EmployerRequest employerRequest) {
        String id = UUID.randomUUID().toString();
        Employer employer = new Employer(id, employerRequest.name(), employerRequest.logo_path(),
                employerRequest.website(), employerRequest.email());
        repoEmployer.saveEmployer(employer);
        return "redirect:/employer/list";
    }

    // @GetMapping(value = "/update/{id}")
    // public String updateEmployer(Model model, @PathVariable String id) {
    // Optional<Employer> employer = Optional.of(repoEmployer.findEmployerByID(id));
    // if (employer.isPresent()) {
    // Employer currentEmp = employer.get();
    // model.addAttribute("employerReq", new EmployerRequest(currentEmp.getId(),
    // currentEmp.getName(),
    // currentEmp.getWebsite(),
    // currentEmp.getEmail(),
    // currentEmp.getLogo_path()));
    // model.addAttribute("employer", currentEmp);
    // }
    // return "Employer_update";
    // }
    @GetMapping(value = "/update/{id}")
    public String updateEmployer(Model model, @PathVariable String id) {
        Employer employer = repoEmployer.findEmployerByID(id);
        model.addAttribute("employers", employer);
        return "Employer_update";
    }

    @PostMapping(value = "/update/{id}")
    public String submitUpdateEmployer(@PathVariable String id, @ModelAttribute EmployerRequest employerRequest) {
        Employer employer = new Employer(id, employerRequest.name(), employerRequest.logo_path(),
                employerRequest.website(), employerRequest.email());
        repoEmployer.updateEmployer(employer);
        return "redirect:/employer/list";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteEmployer(@PathVariable String id) {
        repoEmployer.deleteEmployerById(id);

        return "redirect:/employer/list";
    }
}
