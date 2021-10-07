package com.swoqe.librarianship.repository;

import com.swoqe.librarianship.model.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenreJpaRepository extends JpaRepository<GenreEntity, UUID> {
}
