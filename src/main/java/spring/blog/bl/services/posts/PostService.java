package spring.blog.bl.services.posts;

import java.util.List;

import spring.blog.bl.dto.PostDto;
import spring.blog.web.form.PostForm;

public interface PostService {

    public void savePost(PostForm postForm);

    public List<PostDto> getAllPosts();

    public PostDto getPostById(Long id);

    public void updatePost(PostForm postForm);

    public void deletePostById(Long id);

    public List<PostDto> getSearchPosts(String searchKey);
}
