package spring.blog.persistence.dao.resetPassword;

import spring.blog.persistence.entity.ResetPassword;

public interface ResetPasswordDao {

    public void save(ResetPassword resetPassword);

    public void update(ResetPassword resetPassword);

    public ResetPassword findByTokenDao(String token);
}
