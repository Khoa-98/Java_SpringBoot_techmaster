package com.my_jobhunt.job_hunt.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Applicant {

    /**
     * Thêm sửa xoá Applicant:
     * - Chọn job từ danh sách job hiện có
     * - Họ và tên
     * - Email
     * - Điện thoại
     * - Mô tả kỹ năng
     */
    private String id;
    private String job_id;
    private String emp_id;
    private String name;
    private String email;
    private String phone;
    private List<Skill> skills;

}