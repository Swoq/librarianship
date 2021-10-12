package com.swoqe.librarianship.security;

import com.swoqe.librarianship.security.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SecurityUserRepository extends JpaRepository<SecurityUser, UUID> {
    Optional<SecurityUser> findByUsername(String username);
}
