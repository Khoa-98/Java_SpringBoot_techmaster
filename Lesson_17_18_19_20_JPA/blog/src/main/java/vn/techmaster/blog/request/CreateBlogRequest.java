package vn.techmaster.blog.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateBlogRequest {
    private String title;
    private String description;
    private String content;
    private String thumbnail;
    private int status;
    private List<Integer> categories;
}
