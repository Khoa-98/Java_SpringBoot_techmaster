package vn.techmaster.exam_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.exam_jpa.repository.CourseRepository;
import vn.techmaster.exam_jpa.repository.TopicRepository;
import vn.techmaster.exam_jpa.service.CourseService;

@Controller
@RequestMapping("/api/v1/course")
public class WebController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/list")
    public String getHomePage(Model model, String keyword) {
        if (keyword != null) {
            model.addAttribute("courses", courseService.findCourseByKeyword(keyword));
        } else {
            model.addAttribute("courses", courseService.getAllCourses());
        }
        model.addAttribute("topics", topicRepository.findAll());
        return "web/course-list";
    }

    @GetMapping("/list/topic/{topicId}")
    public String getCourseByTopic(@PathVariable Long topicId, Model model) {
        model.addAttribute("topics", topicRepository.findAll());
        model.addAttribute("courses", courseRepository.findAllByTopics_Id(topicId));
        return "web/course-list";

    }

    @GetMapping("/online_list")
    public String getOnlineListCourse(Model model) {
        model.addAttribute("courses", courseService.getAllCoursesOnline());
        model.addAttribute("topics", topicRepository.findAll());
        return "web/course-online-list";
    }

    @GetMapping("/onlab_list")
    public String getOnLabListCourse(Model model) {
        model.addAttribute("courses", courseService.getAllCoursesOnlab());
        model.addAttribute("topics", topicRepository.findAll());
        return "web/course-onlab-list";
    }

    @GetMapping("/detail/{id}/{slug}")
    public String getDetailCourseById(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourseInfoById(id));

        return "web/detail";
    }
}
