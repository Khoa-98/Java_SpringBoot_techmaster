package vn.techmaster.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.techmaster.blog.repository.CategoryRepository;
import vn.techmaster.blog.repository.CommentRepository;
import vn.techmaster.blog.service.BlogService;

@Controller
public class WebController {



    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CommentRepository commentRepository;


    @GetMapping("/")
    public String getHomePage(Model model){
        model.addAttribute("blogs", blogService.getAllBlogInfo());
        model.addAttribute("blogPopulars", blogService.getBlogPopular(5));
        model.addAttribute("categories", categoryRepository.getCategoriesPopular(5));
        return "web/index";
    }

    @GetMapping("/blogs/{id}/{slug}")
    public String getDetailPage(@PathVariable String id, Model model){
        model.addAttribute("blog", blogService.gteBlogInfoById(id));
        model.addAttribute("comments",commentRepository.getCommentsByBlogId(id) );

        return "web/detail";
    }
    @GetMapping("/about")
    public String getAboutPage(){
        return "web/about";
    }
    @GetMapping("/contact")
    public String getContactPage(){
        return "web/contact";
    }
}
