package com.swoqe.librarianship.security;

import com.swoqe.librarianship.model.BaseSqlEntity;
import com.swoqe.librarianship.model.ModelConstants;
import com.swoqe.librarianship.model.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = ModelConstants.SECURITY_USERS_TABLE)
public class SecurityUser extends BaseSqlEntity implements UserDetails, Serializable {

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private boolean enabled = true;

    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> authorities = new HashSet<>();

    @OneToOne
    private UserEntity userEntity;

    public SecurityUser() {
    }

    public SecurityUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }
}
