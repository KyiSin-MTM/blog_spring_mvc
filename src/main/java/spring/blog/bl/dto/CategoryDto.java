package spring.blog.bl.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.blog.persistence.entity.Category;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    
    private Long id;
    
    private String name;
    
    private Date created_at;
    
    private Date updated_at;
    
    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.created_at = category.getCreated_at();
        this.updated_at = category.getUpdated_at();
    }
}
