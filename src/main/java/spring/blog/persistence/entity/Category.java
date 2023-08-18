package spring.blog.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.blog.bl.dto.CategoryDto;
import spring.blog.web.form.CategoryForm;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @CreationTimestamp
    private Timestamp created_at;
    
    @UpdateTimestamp
    private Timestamp updated_at;    
    
    public Category(CategoryForm categoryForm) {
        this.name = categoryForm.getName();
    }
    
    public Category(CategoryDto categoryDto) {
        this.id = categoryDto.getId();
        this.name = categoryDto.getName();
        this.created_at = (Timestamp)categoryDto.getCreated_at();
        this.updated_at = (Timestamp)categoryDto.getUpdated_at();
    }
}
