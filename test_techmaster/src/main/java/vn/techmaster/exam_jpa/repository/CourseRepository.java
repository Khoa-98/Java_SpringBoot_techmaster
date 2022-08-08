package vn.techmaster.exam_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import vn.techmaster.exam_jpa.dto.CourseInfo;
import vn.techmaster.exam_jpa.entity.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(name = "getAllCourseInfo" , nativeQuery = true)
    List<CourseInfo> getAllCourseInfo();

    @Query(name = "getAllCourseInfoOnLab" , nativeQuery = true)
    List<CourseInfo> getAllCourseInfoOnLab();

    @Query(name = "getAllCourseInfoOnLine" , nativeQuery = true)
    List<CourseInfo> getAllCourseInfoOnLine();

    Course getCourseById(Long id);

    List<Course> findAllByTopics_Id(Long id);





    

}