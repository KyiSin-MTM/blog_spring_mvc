package spring.blog.persistence.dao.posts;

import java.util.List;

import spring.blog.persistence.entity.Post;

public interface PostDao {

    public void savePostDao(Post post);

    public List<Post> getAllPostsDao();

    public Post getPostByIdDao(Long id);

    public void updatePostDao(Post post);

    public void deletePostByIdDao(Post post);

    public List<Post> getSearchPostsDao(String searchKey);
}
