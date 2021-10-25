package com.swoqe.librarianship.security;

import com.swoqe.librarianship.model.ModelConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = ModelConstants.AUTHORITIES_TABLE_NAME)
@NoArgsConstructor
public class CustomAuthority implements GrantedAuthority {

    @Id
    @Enumerated(value = EnumType.STRING)
    private AuthoritiesNames authority;

    public CustomAuthority(AuthoritiesNames authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority.name();
    }
}