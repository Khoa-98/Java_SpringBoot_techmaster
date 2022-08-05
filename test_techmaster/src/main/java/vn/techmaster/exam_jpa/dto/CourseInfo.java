package vn.techmaster.exam_jpa.dto;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CourseInfo {
    private Long id;
    private String name;
    private String slug;
    private Integer type;
    private String description;
    private String thumbnail;
    private SupporterInfo supporterInfo;

    public CourseInfo(Long id, String name, String slug, int type, String description, String thumbnail, String supporterInfo) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.type = type;
        this.description = description;
        this.thumbnail = thumbnail;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.supporterInfo = objectMapper.readValue(supporterInfo, SupporterInfo.class);
        } catch (Exception e) {
            this.supporterInfo = null;
        }

    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class SupporterInfo {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String avatar;
}
