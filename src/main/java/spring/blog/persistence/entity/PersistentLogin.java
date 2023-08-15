package spring.blog.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "persistent_logins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersistentLogin {
    
    @Column(name = "username")
    private String userName;

    @Id
    private String series;
    
    private String token;
    
    @Column(name = "last_used")
    @UpdateTimestamp
    private Timestamp lastUsed;
}
