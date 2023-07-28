package spring.blog.persistence.dao.posts.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.blog.persistence.dao.posts.PostDao;
import spring.blog.persistence.entity.Post;

@Repository
@Transactional
public class PostDaoImpl implements PostDao {
	
	private static final String TABLE_NAME = "Post";
	
	private static final String SELECT_STMT = "FROM " + TABLE_NAME;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void savePostDao(Post post) {
		this.sessionFactory.getCurrentSession().save(post);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getAllPostsDao() {
		// TODO Auto-generated method stub
		StringBuilder stmt = new StringBuilder(SELECT_STMT);
		return this.sessionFactory.getCurrentSession().createQuery(stmt.toString()).list();
	}

	@Override
	public Post getPostByIdDao(Long id) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().get(Post.class, id);
	}

	@Override
	public void updatePostDao(Post post) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().merge(post);
	}

	@Override
	public void deletePostByIdDao(Post post) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(post);
	}
}
