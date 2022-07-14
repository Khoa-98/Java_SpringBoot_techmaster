package vn.techmaster.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import  vn.techmaster.blog.entity.Category;
import vn.techmaster.blog.repository.CategoryRepository;

@SpringBootTest
public class CategoryTest {


    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void generate_category_String_test(){
        List<Category> categories = new ArrayList<>();


    }

    @Test
    void get_allCategory_test(){
        List<Category> categories = categoryRepository.findAll();
        System.out.println(categories);
    }
}
