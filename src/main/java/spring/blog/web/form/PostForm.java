package spring.blog.web.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.blog.bl.dto.PostDto;

/**
 * <h2>PostForm Class</h2>
 * <p>
 * Process for Displaying PostForm
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostForm {

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
    @NotBlank(message = "Title field is required")
    private String title;

    /**
     * <h2>description</h2>
     * <p>
     * description
     * </p>
     */
    @NotBlank(message = "Description field is required")
    private String description;

    /**
     * <h2>Constructor for PostForm</h2>
     * <p>
     * Constructor for PostForm
     * </p>
     * 
     * @param postDto
     */
    public PostForm(PostDto postDto) {
        this.id = postDto.getId();
        this.title = postDto.getTitle();
        this.description = postDto.getDescription();
    }
}
