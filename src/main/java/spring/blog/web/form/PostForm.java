package spring.blog.web.form;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.blog.bl.dto.PostDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostForm {
	
	private Long id;

	@NotBlank(message = "Title field is required")
	private String title;
	
	@NotBlank(message = "Description field is required")
	private String description;
	
	public PostForm(PostDto postDto) {
		this.id = postDto.getId();
		this.title = postDto.getTitle();
		this.description = postDto.getDescription();
	}

}
