package vn.techmaster.hellojpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*
 * @Table:
 * default của nó là tên của class. hoặc có thể thay đổi tên bằng cách định
 * nghĩa trực tiếp
 * vd: @table(name = "employers")
 */
@Table
@Entity

public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String website;
}
