package spring.blog.bl.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.blog.persistence.entity.Post;
import spring.blog.persistence.entity.User;

/**
 * <h2>PostDto Class</h2>
 * <p>
 * Process for Displaying PostDto
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    /**
     * <h2>id</h2>
     * <p>
     * id
     * </p>
     */
    private Long id;

    /**
     * <h2>title</h2>
     * <p>
     * title
     * </p>
     */
    private String title;

    /**
     * <h2>description</h2>
     * <p>
     * description
     * </p>
     */
    private String description;
   
    /**
     * <h2> user</h2>
     * <p>
     * user
     * </p>
     */
    private User user;

    /**
     * <h2>created_at</h2>
     * <p>
     * created_at
     * </p>
     */
    private Date created_at;

    /**
     * <h2>Constructor for PostDto</h2>
     * <p>
     * Constructor for PostDto
     * </p>
     * 
     * @param post
     */
    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.user = post.getUser();
        this.created_at = post.getCreated_at();
    }
}
