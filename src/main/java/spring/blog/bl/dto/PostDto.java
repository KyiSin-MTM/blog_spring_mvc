package spring.blog.bl.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.blog.persistence.entity.Post;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	
	private Long id;
	private String title;
	private String description;
	private Timestamp created_at;
	
	public PostDto(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.description = post.getDescription();
		this.created_at = post.getCreated_at();
	}
}
