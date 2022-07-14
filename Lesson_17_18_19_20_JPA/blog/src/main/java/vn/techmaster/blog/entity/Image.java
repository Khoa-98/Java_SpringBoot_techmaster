package vn.techmaster.blog.entity;


import com.github.javafaker.Company;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import vn.techmaster.blog.dto.UserImageInfo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@SqlResultSetMapping(
        name = "ImageUploadByUserInfo",
        classes = @ConstructorResult(
                targetClass = UserImageInfo.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "images"),
                }
        )
)
@NamedNativeQuery(
        name = "getImageUploadByUserInfo",
        query = "SELECT u.id, u.name, JSON_ARRAYAGG(JSON_OBJECT(\"id\", i.id, \"url\",i.url, \"created_at\", date_format(i.created_at, '%d/%m/%Y'))) as images\n" +
                "FROM image i \n" +
                "RIGHT JOIN `user` u \n" +
                "ON i.user_id = u.id \n" +
                "GROUP BY u.id",
        resultSetMapping = "ImageUploadByUserInfo"
)


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "url")
    private String url;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now().minusMonths(2);
    }
}