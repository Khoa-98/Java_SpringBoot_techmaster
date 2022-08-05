package vn.techmaster.exam_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.exam_jpa.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}