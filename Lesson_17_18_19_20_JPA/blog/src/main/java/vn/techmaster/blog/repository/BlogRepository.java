package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.techmaster.blog.dto.BlogInfo;
import vn.techmaster.blog.entity.Blog;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, String> {
    @Query(name = "getAllBlogInfo", nativeQuery = true)
    List<BlogInfo> getAllBlogInfo();

    List<Blog> getBlogsByUser_Id(Integer id);
    Blog getBlogById(String id);
    void deleteBlogById(String id);
}