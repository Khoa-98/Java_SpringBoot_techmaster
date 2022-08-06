package vn.techmaster.exam_jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.exam_jpa.entity.Topic;
import vn.techmaster.exam_jpa.repository.TopicRepository;

import java.util.List;

@SpringBootTest
public class TopicTest {

    @Autowired
    private TopicRepository topicRepository;

    @Test
    void getTopicTest(){
        List<Topic> topics = topicRepository.findAll();
        System.out.println(topics);
    }
}
