package vn.techmaster.exam_jpa.request;

import lombok.*;
import vn.techmaster.exam_jpa.entity.Topic;
import vn.techmaster.exam_jpa.entity.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseCreateRequest {
        private String name;
        private String description;
        private Integer type;
        private String thumbnail;
        private Long supporter;
        private List<Long> topics;
}
