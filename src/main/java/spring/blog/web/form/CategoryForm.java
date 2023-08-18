package spring.blog.web.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.blog.persistence.entity.Category;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryForm {
    
    private Long id;
    
    @NotBlank(message = "Category name field is required.")
    private String name;
    
    public CategoryForm(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
