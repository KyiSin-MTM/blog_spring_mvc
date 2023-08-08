package spring.blog.persistence.dao.posts.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.blog.persistence.dao.posts.PostDao;
import spring.blog.persistence.entity.Post;

/**
 * <h2>PostDaoImpl Class</h2>
 * <p>
 * Process for Displaying PostDaoImpl
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@Repository
@Transactional
public class PostDaoImpl implements PostDao {

    /**
     * <h2>TABLE_NAME</h2>
     * <p>
     * TABLE_NAME
     * </p>
     */
    private static final String TABLE_NAME = "Post";

    /**
     * <h2>SELECT_STMT</h2>
     * <p>
     * SELECT_STMT
     * </p>
     */
    private static final String SELECT_STMT = "FROM " + TABLE_NAME;

    /**
     * <h2>sessionFactory</h2>
     * <p>
     * sessionFactory
     * </p>
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * <h2>savePostDao</h2>
     * <p>
     * save post
     * </p>
     * 
     * @param post
     */
    @Override
    public void savePostDao(Post post) {
        this.sessionFactory.getCurrentSession().save(post);
    }

    /**
     * <h2>getAllPostsDao</h2>
     * <p>
     * post list
     * </p>
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Post> getAllPostsDao() {
        StringBuilder stmt = new StringBuilder(SELECT_STMT);
        return this.sessionFactory.getCurrentSession().createQuery(stmt.toString()).list();
    }

    /**
     * <h2>getPostByIdDao</h2>
     * <p>
     * get post by id
     * </p>
     * 
     * @param id
     * @return
     */
    @Override
    public Post getPostByIdDao(Long id) {
        return this.sessionFactory.getCurrentSession().get(Post.class, id);
    }

    /**
     * <h2>updatePostDao</h2>
     * <p>
     * update post
     * </p>
     * 
     * @param post
     */
    @Override
    public void updatePostDao(Post post) {
        // TODO Auto-generated method stub
        this.sessionFactory.getCurrentSession().merge(post);
    }

    /**
     * <h2>deletePostByIdDao</h2>
     * <p>
     * delete post
     * </p>
     * 
     * @param post
     */
    @Override
    public void deletePostByIdDao(Post post) {
        this.sessionFactory.getCurrentSession().delete(post);
    }

    /**
     * <h2>getSearchPostsDao</h2>
     * <p>
     * search post
     * </p>
     * 
     * @param searchKey
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Post> getSearchPostsDao(String searchKey) {
        String stmt = "SELECT p FROM Post p WHERE p.title LIKE CONCAT('%', :searchKey, '%') OR p.description LIKE CONCAT('%', :searchKey, '%')";
        Query<Post> query = this.sessionFactory.getCurrentSession().createQuery(stmt).setParameter("searchKey",
                searchKey);
        return query.getResultList();
    }
}
