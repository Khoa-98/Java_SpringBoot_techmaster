package vn.techmaster.exam_jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.exam_jpa.dto.CourseInfo;
import vn.techmaster.exam_jpa.entity.Course;
import vn.techmaster.exam_jpa.repository.CourseRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseInfo> getAllCourses(){
        return courseRepository.getAllCourseInfo();
    }

    public CourseInfo getCourseInfoById(Long id){
        Optional<CourseInfo> courseInfoOptional=  courseRepository.getAllCourseInfo()
                .stream()
                .filter(courseInfo -> courseInfo.getId().equals(id))
                .findFirst();
        return courseInfoOptional.orElse(null);
    }

    public List<CourseInfo> getAllCoursesOnlab(){
        return courseRepository.getAllCourseInfoOnLab();
    }

    public List<CourseInfo> getAllCoursesOnline(){
        return courseRepository.getAllCourseInfoOnLine();
    }

    // tim kiem theo keyword
    public List<CourseInfo> findCourseByKeyword(String keyword) {
        List<CourseInfo> courseInfoList = courseRepository.getAllCourseInfo().stream()
                .filter(j -> j.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        return courseInfoList;
    }

    // loc theo topic
    public List<CourseInfo> findCourseByTopic(String keyword) {
        List<CourseInfo> courseInfoList = courseRepository.getAllCourseInfo().stream()
                .filter(j -> j.get().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        return courseInfoList;
    }


}
