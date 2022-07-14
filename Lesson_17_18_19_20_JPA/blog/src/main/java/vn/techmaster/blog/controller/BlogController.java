package vn.techmaster.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.blog.entity.Blog;
import vn.techmaster.blog.repository.BlogRepository;
import vn.techmaster.blog.repository.CategoryRepository;
import vn.techmaster.blog.request.CreateBlogRequest;
import vn.techmaster.blog.service.BlogService;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BlogRepository blogRepository;


    // Danh sách tất cả bài viết
    @GetMapping("/admin/blogs")
    public String getBlogPage(Model model) {
        // TODO : Bổ sung thêm phân trang
        model.addAttribute("blogs", blogService.getAllBlogDto());

        return "admin/blog/blog-index";
    }

    // Danh sách bài viết của tôi
    @GetMapping("/admin/blogs/own-blogs")
    public String getOwnBlogPage(Model model) {
        // TODO : Về sau userId sẽ là id của user đang đăng nhập
        Integer userId = 1;

        model.addAttribute("blogs", blogService.getAllBlogDtoByUserId(userId));

        return "admin/blog/blog-yourself";
    }

    // Tạo bài viết
    @GetMapping("/admin/blogs/create")
    public String getBlogCreatePage(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/blog/blog-create";
    }
    // Tạo bài viết
    @PostMapping("/api/admin/blogs")
    public ResponseEntity<?> createBlog(@RequestBody CreateBlogRequest request) {
        // TODO : Về sau userId sẽ là id của user đang đăng nhập
        Integer userId = 1;

        // Tạo blog
        Blog blog = blogService.createBlog(userId, request);

        // Trả về kết quả
        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }

    // Chi tiết bài viết
    @GetMapping("/admin/blogs/{id}/detail")
    public String getBlogDetailPage(@PathVariable String id, Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("blog", blogService.getBlogDtoById(id));
        return "admin/blog/blog-detail";
    }

    @DeleteMapping("/admin/blogs/{id}/delete")
    public String deleteBlogById(@PathVariable String id, Model model){
        model.addAttribute("blog", blogRepository.findById(id));
        blogRepository.deleteById(id);

        return "admin/blog/blog-index";
    }


}
