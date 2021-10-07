package com.example.springlab2.repository;

import com.example.springlab2.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorJpaRepository extends JpaRepository<AuthorEntity, UUID> {

}
