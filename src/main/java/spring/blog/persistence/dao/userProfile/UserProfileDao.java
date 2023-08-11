package spring.blog.persistence.dao.userProfile;

import spring.blog.persistence.entity.UserProfile;

public interface UserProfileDao {

    public void dbSave(UserProfile userProfile);

    public UserProfile dbFindById(Long id);

    public void dbUpdate(UserProfile userProfile);
}
