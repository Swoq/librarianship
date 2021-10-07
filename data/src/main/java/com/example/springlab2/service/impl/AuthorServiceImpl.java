package com.example.springlab2.service.impl;

import com.example.springlab2.dto.AuthorDto;
import com.example.springlab2.model.entity.AuthorEntity;
import com.example.springlab2.repository.AuthorJpaRepository;
import com.example.springlab2.service.AbstractJpaService;
import com.example.springlab2.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class AuthorServiceImpl extends AbstractJpaService<AuthorDto, AuthorEntity, AuthorJpaRepository> implements AuthorService {

    public AuthorServiceImpl(AuthorJpaRepository repository) {
        super(repository);
    }

    @Override
    public Set<AuthorDto> findAll() {
        return null;
    }

    @Override
    public AuthorDto findById(UUID id) {
        return null;
    }

    @Override
    public AuthorDto save(AuthorDto object) {
        return null;
    }

    @Override
    public void delete(AuthorDto object) {

    }

    @Override
    public void deleteById(UUID id) {

    }
}
