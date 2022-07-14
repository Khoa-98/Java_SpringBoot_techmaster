package vn.techmaster.blog.util;

import java.util.List;

import vn.techmaster.blog.dto.BlogDto;


public class Utils {
    public static String generateCategoryString(List<BlogDto.CategoryDto> categories){
        List<String> categoriesName = categories.stream().map(BlogDto.CategoryDto::getName).toList();

        String[] itemArray = new String[categoriesName.size()];
        itemArray = categoriesName.toArray(itemArray);

        return String.join(", ", itemArray);
    }

}
