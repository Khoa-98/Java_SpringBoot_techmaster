package vn.techmaster.exam_jpa.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
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
        private MultipartFile thumbnail;
        private Long supporter;
        private List<Long> topics;
}
