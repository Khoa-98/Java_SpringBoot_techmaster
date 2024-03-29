package vn.techmaster.exam_jpa;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class ExamJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamJpaApplication.class, args);
    }

    @Bean
    public Faker faker(){
        return  new Faker();
    }

    @Bean
    public Slugify slugify() {
        return new Slugify();
    }

    @Bean
    public Random random(){
        return new Random();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
