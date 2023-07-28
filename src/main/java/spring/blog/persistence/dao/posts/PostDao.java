package spring.blog.persistence.dao.posts;

import java.util.List;

import spring.blog.persistence.entity.Post;

public interface PostDao {
	
	void savePostDao(Post post);

	List<Post> getAllPostsDao();

	Post getPostByIdDao(Long id);

	void updatePostDao(Post post);

	void deletePostByIdDao(Post post);

}
