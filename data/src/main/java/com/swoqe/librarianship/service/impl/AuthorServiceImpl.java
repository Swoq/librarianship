package com.swoqe.librarianship.service.impl;

import com.swoqe.librarianship.dto.AuthorDto;
import com.swoqe.librarianship.model.entity.AuthorEntity;
import com.swoqe.librarianship.repository.AuthorJpaRepository;
import com.swoqe.librarianship.service.AbstractJpaService;
import com.swoqe.librarianship.service.AuthorService;
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
