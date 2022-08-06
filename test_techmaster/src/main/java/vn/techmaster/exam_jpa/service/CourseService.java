package vn.techmaster.exam_jpa.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.exam_jpa.dto.CourseDto;
import vn.techmaster.exam_jpa.dto.CourseInfo;
import vn.techmaster.exam_jpa.entity.Course;
import vn.techmaster.exam_jpa.entity.Topic;
import vn.techmaster.exam_jpa.entity.User;
import vn.techmaster.exam_jpa.repository.CourseRepository;
import vn.techmaster.exam_jpa.repository.TopicRepository;
import vn.techmaster.exam_jpa.repository.UserRepository;
import vn.techmaster.exam_jpa.request.CourseCreateRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;


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
//    public List<CourseInfo> findCourseByTopic(String keyword) {
//        List<CourseInfo> courseInfoList = courseRepository.getAllCourseInfo().stream()
//                .filter(j -> j.get().toLowerCase().contains(keyword.toLowerCase()))
//                .collect(Collectors.toList());
//        return courseInfoList;
//    }

    // Lấy danh sách tất cả course ở dạng DTO
    public List<CourseDto> getAllCourseDto() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    // Tao khoa hoc
    public Course createCourse(CourseCreateRequest request){
        // lay thong tin cua supporter
        User supporter = userRepository.getUserById(request.getSupporter());

        // lay thong tin cua topics
        List<Topic> topics = topicRepository.getByIdIn(request.getTopics());

        // tao course
        Course course = Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .type(request.getType())
                .topics(topics)
                .user(supporter)
                .thumbnail(request.getThumbnail())
                .build();
        courseRepository.save(course);
        return course;
    }


}
