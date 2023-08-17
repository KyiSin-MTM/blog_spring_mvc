package spring.blog.bl.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import spring.blog.persistence.entity.Role;

/**
 * <h2>CustomUserDetails Class</h2>
 * <p>
 * Process for Displaying CustomUserDetails
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
@AllArgsConstructor
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {

    /**
     * <h2>serialVersionUID</h2>
     * <p>
     * serialVersionUID
     * </p>
     */
    private static final long serialVersionUID = -7027756515208371329L;

    /**
     * <h2>username</h2>
     * <p>
     * username
     * </p>
     */
    private String username;

    /**
     * <h2>password</h2>
     * <p>
     * password
     * </p>
     */
    private String password;

    /**
     * <h2>roles</h2>
     * <p>
     * roles
     * </p>
     */
    private List<Role> roles;

    /**
     * <h2>getAuthorities</h2>
     * <p>
     * get authorities
     * </p>
     * 
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : this.roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /**
     * <h2>getPassword</h2>
     * <p>
     * get password
     * </p>
     * 
     * @return
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * <h2>getUsername</h2>
     * <p>
     * get user name
     * </p>
     * 
     * @return
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * <h2>isAccountNonExpired</h2>
     * <p>
     * is account not expired
     * </p>
     * 
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * <h2>isAccountNonLocked</h2>
     * <p>
     * is account not locked
     * </p>
     * 
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * <h2>isCredentialsNonExpired</h2>
     * <p>
     * is credential is not expired
     * </p>
     * 
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * <h2>isEnabled</h2>
     * <p>
     * is enable
     * </p>
     * 
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
    
//    @Override
//    public boolean equals(Object otherUser) {
//        if (this == otherUser) return true;
//        if (otherUser == null || getClass() != otherUser.getClass()) return false;
//
//        UserDetails that = (UserDetails) otherUser;
//
//        return username != null ? username.equals(that.getUsername()) : that.getUsername() == null;
//    }
//
//    @Override
//    public int hashCode() {
//        return username != null ? username.hashCode() : 0;
//    }
    
    @Override
    public boolean equals(Object otherUser) {
        if (this == otherUser) {
            return true;
        }
        if (otherUser == null || getClass() != otherUser.getClass()) {
            return false;
        }
        
        CustomUserDetails that = (CustomUserDetails) otherUser;
        return Objects.equals(getUsername(), that.getUsername()); // Use a unique identifier, such as username or user ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername()); // Use a unique identifier, such as username or user ID
    }
}
