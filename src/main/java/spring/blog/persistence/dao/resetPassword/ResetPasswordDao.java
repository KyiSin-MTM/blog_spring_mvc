package spring.blog.persistence.dao.resetPassword;

import spring.blog.persistence.entity.ResetPassword;

public interface ResetPasswordDao {

    void save(ResetPassword resetPassword);

    void update(ResetPassword resetPassword);

    ResetPassword findByTokenDao(String token);
}
