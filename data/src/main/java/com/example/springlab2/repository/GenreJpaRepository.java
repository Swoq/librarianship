package com.example.springlab2.repository;

import com.example.springlab2.model.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GenreJpaRepository extends JpaRepository<GenreEntity, UUID> {
}
