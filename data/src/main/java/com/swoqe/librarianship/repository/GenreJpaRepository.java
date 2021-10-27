package com.swoqe.librarianship.repository;

import com.swoqe.librarianship.model.entity.GenreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenreJpaRepository extends JpaRepository<GenreEntity, UUID> {
    Page<GenreEntity> findByGenreNameContains(String toString, Pageable toPageable);
}
