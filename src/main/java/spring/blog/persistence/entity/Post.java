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

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@CreationTimestamp
	private Timestamp created_at;

	@UpdateTimestamp
	private Timestamp updated_at;

	public Post() {
		super();
	}

	public Post(PostForm postForm) {
		this.title = postForm.getTitle();
		this.description = postForm.getDescription();
	}

	public Post(PostDto postDTO) {
		this.title = postDTO.getTitle();
		this.description = postDTO.getDescription();
	}

}