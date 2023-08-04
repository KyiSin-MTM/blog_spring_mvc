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

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostDao postDao;

	@Override
	public void savePost(PostForm postForm) {
		// TODO Auto-generated method stub
		Post post = new Post();
		post.setTitle(postForm.getTitle());
		post.setDescription(postForm.getDescription());
		
		this.postDao.savePostDao(post);
	}

	@Override
	public List<PostDto> getAllPosts() {
		// TODO Auto-generated method stub
		List<Post> posts = this.postDao.getAllPostsDao();
        List<PostDto> postDtoList = posts.stream().map(post -> {
            PostDto postDTO = new PostDto(post);
            return postDTO;
        }).collect(Collectors.toList());
        return postDtoList;
	}

	@Override
	public PostDto getPostById(Long id) {
		// TODO Auto-generated method stub
		Post post = this.postDao.getPostByIdDao(id);
		PostDto postDto = new PostDto(post);
		return postDto;
	}

	@Override
	public void updatePost(PostForm postForm) {
		// TODO Auto-generated method stub
		Post post = this.postDao.getPostByIdDao(postForm.getId());
		post.setTitle(postForm.getTitle());
		post.setDescription(postForm.getDescription());
		
		this.postDao.updatePostDao(post);
	}

	@Override
	public void deletePostById(Long id) {
		// TODO Auto-generated method stub
		Post post = this.postDao.getPostByIdDao(id);
		
		this.postDao.deletePostByIdDao(post);
	}

	@Override
	public List<PostDto> getSearchPosts(String searchKey) {
		// TODO Auto-generated method stub
		if(searchKey != "") {
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
