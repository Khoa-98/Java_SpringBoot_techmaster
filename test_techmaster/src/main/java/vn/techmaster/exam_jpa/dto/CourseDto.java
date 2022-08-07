package vn.techmaster.exam_jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CourseDto implements Serializable {
    private  Long id;
    private String name ;
    private String slug;
    private Integer type;
    private  String description;
    private String thumbnail;
    private UserDto1 user;
    private List<TopicDto> topics = new ArrayList<>();

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserDto1 implements Serializable{
        private Long id;
        private  String name;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TopicDto implements Serializable{
        private Long id;
        private  String name;
    }
}


