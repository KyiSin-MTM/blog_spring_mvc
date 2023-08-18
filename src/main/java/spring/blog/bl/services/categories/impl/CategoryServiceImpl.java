package spring.blog.bl.services.categories.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.blog.bl.dto.CategoryDto;
import spring.blog.bl.services.categories.CategoryService;
import spring.blog.persistence.entity.Category;
import spring.blog.persistence.repository.CategoryRepository;
import spring.blog.web.form.CategoryForm;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void dbSaveCategory(@Valid CategoryForm categoryForm) {
        Category category = new Category(categoryForm);
        this.categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> getAllcategories() {
        List<CategoryDto> categories = this.categoryRepository.findAll().stream()
                .map(category -> new CategoryDto(category)).toList();
        return categories;
    }

    @Override
    public Category getCategoryById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Category Id: " + id));
    }

    @Override
    public void doUpdateCategory(@Valid CategoryForm categoryForm) {
        Category category = getCategoryById(categoryForm.getId());
        category.setName(categoryForm.getName());
        this.categoryRepository.save(category);
    }

    @Override
    public void doDeleteCategory(Long id) {
        this.categoryRepository.deleteById(id);
    }
}
