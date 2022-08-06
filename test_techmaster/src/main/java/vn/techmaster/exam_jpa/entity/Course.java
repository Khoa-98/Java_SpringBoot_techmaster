package vn.techmaster.exam_jpa.entity;

import lombok.*;
import vn.techmaster.exam_jpa.dto.CourseInfo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SqlResultSetMapping(
        name = "listCourseInfo",
        classes = @ConstructorResult(
                targetClass = CourseInfo.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "slug", type = String.class),
                        @ColumnResult(name = "type", type = Integer.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "thumbnail", type = String.class),
                        @ColumnResult(name = "supporter", type = String.class)
                }
        )
)
@NamedNativeQuery(
        name = "getAllCourseInfo",
        resultSetMapping = "listCourseInfo",
        query = "\n" +
                "select c.id,c.name, c.slug, c.thumbnail, c.type,c.description ,\n" +
                "JSON_OBJECT (\"id\", u.id, \"name\", u.name, \"avatar\", u.avatar,\"phone\", u.phone,\"email\", u.email) as supporter\n" +
                "from course c \n" +
                "left join `user` u \n" +
                "on u.id = c.supporter_id "
)

@NamedNativeQuery(
        name = "getAllCourseInfoOnLab",
        resultSetMapping = "listCourseInfo",
        query = "select c.id,c.name, c.slug, c.thumbnail, c.type,c.description ,\n" +
                "JSON_OBJECT (\"id\", u.id, \"name\", u.name, \"avatar\", u.avatar,\"phone\", u.phone,\"email\", u.email) as supporter\n" +
                "from course c \n" +
                "left join `user` u \n" +
                "on u.id = c.supporter_id \n" +
                "where c.type =0"
)
@NamedNativeQuery(
        name = "getAllCourseInfoOnLine",
        resultSetMapping = "listCourseInfo",
        query = "select c.id,c.name, c.slug, c.thumbnail, c.type,c.description ,\n" +
                "JSON_OBJECT (\"id\", u.id, \"name\", u.name, \"avatar\", u.avatar,\"phone\", u.phone,\"email\", u.email) as supporter\n" +
                "from course c \n" +
                "left join `user` u \n" +
                "on u.id = c.supporter_id \n" +
                "where c.type =1"
)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name = "course")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name = "slug")
    private String slug;

    @Column(name = "type")
    private int type;

    @Column(name = "description")
    private String description;

    @Column(name = "thumbnail")
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supporter_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "course_topics",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "topics_id"))
    private List<Topic> topics = new ArrayList<>();

}