package spring.blog.bl.services.categories;

import java.util.List;

import javax.validation.Valid;

import spring.blog.bl.dto.CategoryDto;
import spring.blog.persistence.entity.Category;
import spring.blog.web.form.CategoryForm;

public interface CategoryService {

    public void dbSaveCategory(@Valid CategoryForm categoryForm);

    public List<CategoryDto> getAllcategories();

    public Category getCategoryById(Long id);

    public void doUpdateCategory(@Valid CategoryForm categoryForm);

    public void doDeleteCategory(Long id);
}
