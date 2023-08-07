package spring.blog.bl.services.posts.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.blog.bl.dto.PostDto;
import spring.blog.bl.services.posts.PostService;
import spring.blog.persistence.dao.posts.PostDao;
import spring.blog.persistence.entity.Post;
import spring.blog.web.form.PostForm;

/**
 * <h2>PostServiceImpl Class</h2>
 * <p>
 * Process for Displaying PostServiceImpl
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Service
public class PostServiceImpl implements PostService {

    /**
     * <h2>postDao</h2>
     * <p>
     * postDao
     * </p>
     */
    @Autowired
    private PostDao postDao;

    /**
     * <h2>savePost</h2>
     * <p>
     * 
     * </p>
     * 
     * @param postForm
     */
    @Override
    public void savePost(PostForm postForm) {
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setDescription(postForm.getDescription());
        this.postDao.savePostDao(post);
    }

    /**
     * <h2>getAllPosts</h2>
     * <p>
     * 
     * </p>
     * 
     * @return
     */
    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = this.postDao.getAllPostsDao();
        List<PostDto> postDtoList = posts.stream().map(post -> {
            PostDto postDTO = new PostDto(post);
            return postDTO;
        }).collect(Collectors.toList());
        return postDtoList;
    }

    /**
     * <h2>getPostById</h2>
     * <p>
     * 
     * </p>
     * 
     * @param id
     * @return
     */
    @Override
    public PostDto getPostById(Long id) {
        Post post = this.postDao.getPostByIdDao(id);
        PostDto postDto = new PostDto(post);
        return postDto;
    }

    /**
     * <h2>updatePost</h2>
     * <p>
     * 
     * </p>
     * 
     * @param postForm
     */
    @Override
    public void updatePost(PostForm postForm) {
        Post post = this.postDao.getPostByIdDao(postForm.getId());
        post.setTitle(postForm.getTitle());
        post.setDescription(postForm.getDescription());
        this.postDao.updatePostDao(post);
    }

    /**
     * <h2>deletePostById</h2>
     * <p>
     * 
     * </p>
     * 
     * @param id
     */
    @Override
    public void deletePostById(Long id) {
        Post post = this.postDao.getPostByIdDao(id);
        this.postDao.deletePostByIdDao(post);
    }

    /**
     * <h2>getSearchPosts</h2>
     * <p>
     * 
     * </p>
     * 
     * @param searchKey
     * @return
     */
    @Override
    public List<PostDto> getSearchPosts(String searchKey) {
        if (searchKey != "") {
            List<Post> posts = this.postDao.getSearchPostsDao(searchKey);
            List<PostDto> postDtoList = posts.stream().map(post -> {
                PostDto postDto = new PostDto(post);
                return postDto;
            }).collect(Collectors.toList());
            return postDtoList;
        } else {
            return this.getAllPosts();
        }
    }
}