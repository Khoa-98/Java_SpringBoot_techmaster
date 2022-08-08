package vn.techmaster.exam_jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.exam_jpa.dto.CourseDto;
import vn.techmaster.exam_jpa.dto.CourseInfo;
import vn.techmaster.exam_jpa.entity.Course;
import vn.techmaster.exam_jpa.repository.CourseRepository;
import vn.techmaster.exam_jpa.repository.UserRepository;
import vn.techmaster.exam_jpa.service.CourseService;

import java.util.List;

@SpringBootTest
public class courseTest {

    @Autowired
    private CourseRepository courseRepository;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseService courseService;

    @Test
    void getAllCourseInfo_test(){
        List<CourseInfo> courseInfos  = courseRepository.getAllCourseInfo();
        System.out.println(courseInfos);

    }

    @Test
    void getAllCourseDto_Test(){
        List<CourseDto> courseDtos = courseService.getAllCourseDto();
        System.out.println(courseDtos);
    }

    @Test
    void getAllCourse_test() {
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);

    }
    
    @Test
    void getcourseByTopicId() {
        Long id = 2L;
        List<Course> course = courseRepository.findAllByTopics_Id(id);
        System.out.println(course);

    }
}
