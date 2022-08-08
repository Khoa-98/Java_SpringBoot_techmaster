package vn.techmaster.exam_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import vn.techmaster.exam_jpa.entity.Course;
import vn.techmaster.exam_jpa.repository.TopicRepository;
import vn.techmaster.exam_jpa.repository.UserRepository;
import vn.techmaster.exam_jpa.repository.CourseRepository;
import vn.techmaster.exam_jpa.request.CourseCreateRequest;
import vn.techmaster.exam_jpa.request.CourseUpdateRequest;
import vn.techmaster.exam_jpa.service.CourseService;
import vn.techmaster.exam_jpa.service.FileService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;

    // xem danh sach khoa hoc
    @GetMapping
    public String getHomeAdminPage(Model model) {
        model.addAttribute("courses", courseService.getAllCourseDto());
        return "admin/course/course-list";
    }

    // tao khoa hoc
    @GetMapping("/create")
    public String createCoursePage(Model model) {
        model.addAttribute("topics", topicRepository.findAll());
        model.addAttribute("supporters", userRepository.findAll());
        return "admin/course/course-create";
    }

    // Tạo khoa hoc
    @PostMapping("/api/create")
    public ResponseEntity<?> createBlog(@RequestBody CourseCreateRequest request) {

        // Tạo blog
        Course blog = courseService.createCourse(request);

        // Trả về kết quả
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    // lay thong tin khoa hoc
    @GetMapping("/edit/{id}/{slug}")
    public String editCoursePage(@PathVariable Long id, @PathVariable String slug, Model model) {
        model.addAttribute("topics", topicRepository.findAll());
        model.addAttribute("supporters", userRepository.findAll());
        model.addAttribute("course", courseService.getCourseDtoById(id));
        return "admin/course/course-edit";
    }

    // cap nhat thong tin khoa hoc
    @PutMapping("/api/edit/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseUpdateRequest request) {
        Course course = courseService.updateCourse(id, request);
        return ResponseEntity.ok(course);
    }

    // upload file image
    @PostMapping("/api/courses/{id}/upload-file")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file, @PathVariable Long id) {
        String filePath = courseService.uploadFile(id, file);
        return ResponseEntity.ok(filePath);
    }

    // xoa khoa hoc
    @DeleteMapping("/delete/{id}")
    public String deleteCourse(Model model, @PathVariable Long id) {
        model.addAttribute("course", courseRepository.findById(id));
        courseRepository.deleteById(id);
        return "admin/course/course-list";
    }

}
