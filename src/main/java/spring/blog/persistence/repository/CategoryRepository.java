package spring.blog.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.blog.persistence.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
