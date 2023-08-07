package spring.blog.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import spring.blog.bl.dto.PostDto;
import spring.blog.web.form.PostForm;

/**
 * <h2>Post Class</h2>
 * <p>
 * Process for Displaying Post
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
public class Post {

    /**
     * <h2>id</h2>
     * <p>
     * id
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * <h2>title</h2>
     * <p>
     * title
     * </p>
     */
    @Column(name = "title")
    private String title;

    /**
     * <h2>description</h2>
     * <p>
     * description
     * </p>
     */
    @Column(name = "description")
    private String description;

    /**
     * <h2>created_at</h2>
     * <p>
     * created_at
     * </p>
     */
    @CreationTimestamp
    private Timestamp created_at;

    /**
     * <h2>updated_at</h2>
     * <p>
     * updated_at
     * </p>
     */
    @UpdateTimestamp
    private Timestamp updated_at;

    /**
     * <h2>Constructor for Post</h2>
     * <p>
     * Constructor for Post
     * </p>
     */
    public Post() {
        super();
    }

    /**
     * <h2>Constructor for Post</h2>
     * <p>
     * Constructor for Post
     * </p>
     * 
     * @param postForm
     */
    public Post(PostForm postForm) {
        this.title = postForm.getTitle();
        this.description = postForm.getDescription();
    }

    /**
     * <h2>Constructor for Post</h2>
     * <p>
     * Constructor for Post
     * </p>
     * 
     * @param postDTO
     */
    public Post(PostDto postDTO) {
        this.id = postDTO.getId();
        this.title = postDTO.getTitle();
        this.description = postDTO.getDescription();
        this.created_at = postDTO.getCreated_at();
    }
}