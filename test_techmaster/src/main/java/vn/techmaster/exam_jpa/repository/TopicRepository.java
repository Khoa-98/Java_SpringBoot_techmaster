package vn.techmaster.exam_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.exam_jpa.entity.Topic;

import java.util.Collection;
import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> getByIdIn(Collection<Long> ids);

}