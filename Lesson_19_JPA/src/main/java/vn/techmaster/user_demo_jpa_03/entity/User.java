package vn.techmaster.user_demo_jpa_03.entity;

import lombok.*;
import vn.techmaster.user_demo_jpa_03.dto.UserDto;

import javax.persistence.*;
@SqlResultSetMapping(
        name = "userInfo",
        classes = @ConstructorResult(
                targetClass = UserDto.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "email")
                }
        )
)
@NamedNativeQuery(
        name = "getUserInfo",
        resultSetMapping = "userInfo",
        query = "SELECT user.id, user.name, user.email " +
                "FROM user " +
                "WHERE user.email = ?1"
)
@Table(name = "user")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "password")
    private String password;

}