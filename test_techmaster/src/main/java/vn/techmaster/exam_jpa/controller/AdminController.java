package vn.techmaster.exam_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.exam_jpa.entity.Course;
import vn.techmaster.exam_jpa.repository.TopicRepository;
import vn.techmaster.exam_jpa.repository.UserRepository;
import vn.techmaster.exam_jpa.request.CourseCreateRequest;
import vn.techmaster.exam_jpa.service.CourseService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserRepository userRepository;


    // xem danh sach khoa hoc
    @GetMapping
    public String getHomeAdminPage(Model model){
        model.addAttribute("courses", courseService.getAllCourseDto());
        return "admin/course/course-list";
    }

    // tao khoa hoc
    @GetMapping("/create")
    public String createCoursePage(Model model){
        model.addAttribute("topics", topicRepository.findAll());
        model.addAttribute("supporters", userRepository.findAll());
        return "admin/course/course-create";
    }
    // Tạo bài viết
    @PostMapping("/api/create")
    public ResponseEntity<?> createBlog(@RequestBody CourseCreateRequest request) {

        // Tạo blog
        Course blog = courseService.createCourse( request);

        // Trả về kết quả
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }
    // sua khoa hoc
    @GetMapping("/edit/{id}")
    public String editCoursePage(@PathVariable Long id, Model model){

        return "admin/course/course-edit";
    }

    @DeleteMapping("/delete")
    public String deleteCourse(){
        return "admin/course/course-list";
    }

}
