package com.swoqe.librarianship.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomAuthorityRepository extends JpaRepository<CustomAuthority, String> {
}
