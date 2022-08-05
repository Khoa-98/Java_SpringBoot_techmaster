package vn.techmaster.exam_jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.exam_jpa.dto.CourseInfo;
import vn.techmaster.exam_jpa.entity.Course;
import vn.techmaster.exam_jpa.repository.CourseRepository;
import vn.techmaster.exam_jpa.repository.UserRepository;

import java.util.List;

@SpringBootTest
public class courseTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void getAllCourseInfo_test(){
        List<CourseInfo> courseInfos  = courseRepository.getAllCourseInfo();
        System.out.println(courseInfos);

    }
}
