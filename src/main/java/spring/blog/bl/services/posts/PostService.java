package spring.blog.bl.services.posts;

import java.util.List;

import spring.blog.bl.dto.PostDto;
import spring.blog.web.form.PostForm;

public interface PostService {

    void savePost(PostForm postForm);

    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

    void updatePost(PostForm postForm);

    void deletePostById(Long id);

    List<PostDto> getSearchPosts(String searchKey);
}
