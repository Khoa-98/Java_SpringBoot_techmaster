package vn.techmaster.exam_jpa;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.techmaster.exam_jpa.entity.Course;
import vn.techmaster.exam_jpa.entity.Image;
import vn.techmaster.exam_jpa.entity.Topic;
import vn.techmaster.exam_jpa.entity.User;
import vn.techmaster.exam_jpa.repository.CourseRepository;
import vn.techmaster.exam_jpa.repository.ImageRepository;
import vn.techmaster.exam_jpa.repository.TopicRepository;
import vn.techmaster.exam_jpa.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class InitDataTest {

    @Autowired
    private Faker faker;

    @Autowired
    private Slugify slugify;
    @Autowired
    private Random rd;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TopicRepository topicRepository;

    @Test
    void save_user(){
        for (int i = 0; i < 2; i++) {
            User user = User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .phone(faker.phoneNumber().phoneNumber())
                    .build();
            userRepository.save(user);
        }

    }
    
    @Test
    void save_image(){
        for (int i = 0; i < 20; i++) {
            String generateFileId = UUID.randomUUID().toString();
            Image image = Image.builder()
                    .id(generateFileId)
                    .link(faker.company().logo())
                    .build();
            imageRepository.save(image);
        }
    }
    @Test
    void save_avatar_of_user() {
        // Lấy ds user
        List<User> users = userRepository.findAll();

        users.forEach(user -> {
            List<Image> images = imageRepository.findAll();
            String imageRd = images.get(rd.nextInt(images.size())).getLink();
            user.setAvatar(imageRd);
            userRepository.save(user);
        });
    }
    @Test
    void save_topic() {
        for (int i = 0; i < 10; i++) {
            Topic topic = Topic.builder()
                    .name(faker.leagueOfLegends().champion())
                    .build();

            topicRepository.save(topic);
        }
    }

    @Test
    void save_course(){
        List<User> users = userRepository.findAll();
        List<Topic> topics = topicRepository.findAll();
        List<Image> images = imageRepository.findAll();


        for (int i = 0; i < 20; i++) {
            User userRd = users.get(rd.nextInt(users.size()));

            String  imageRd = images.get(rd.nextInt(images.size())).getLink();

            List<Topic> topicsRd = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Topic topicRd = topics.get(rd.nextInt(topics.size()));
                if(!topicsRd.contains(topicRd)) {
                    topicsRd.add(topicRd);
                }
            }
            String name = faker.lorem().sentence(1);
            Course course = Course.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .type(rd.nextInt(2))
                    .description(faker.lorem().sentence(20))
                    .thumbnail(imageRd)
                    .user(userRd)
                    .topics(topicsRd)
                    .build();

            courseRepository.save(course);
        }
    }
}
