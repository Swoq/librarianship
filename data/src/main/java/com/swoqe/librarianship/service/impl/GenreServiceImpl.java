package com.swoqe.librarianship.service.impl;

import com.swoqe.librarianship.dto.GenreDto;
import com.swoqe.librarianship.dto.converter.GenreConverter;
import com.swoqe.librarianship.model.entity.GenreEntity;
import com.swoqe.librarianship.repository.GenreJpaRepository;
import com.swoqe.librarianship.service.AbstractJpaService;
import com.swoqe.librarianship.service.GenreService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class GenreServiceImpl extends AbstractJpaService<GenreDto, GenreEntity, GenreJpaRepository> implements GenreService {

    private final GenreConverter converter;

    public GenreServiceImpl(GenreJpaRepository repository, GenreConverter converter) {
        super(repository);
        this.converter = converter;
    }

    @Override
    public Set<GenreDto> findAll() {
        return converter.createFromEntities(findAllEntities());
    }

    @Override
    public GenreDto findById(UUID id) {
        return converter.toDto(findEntityById(id));
    }

    @Override
    public GenreDto save(GenreDto dto) {
        GenreEntity entity = converter.toEntity(dto);
        GenreEntity savedEntity = saveEntity(entity);
        return converter.toDto(savedEntity);
    }

    @Override
    public void delete(GenreDto dto) {
        GenreEntity entity = converter.toEntity(dto);
        deleteEntity(entity);
    }

    @Override
    public void deleteById(UUID id) {
        deleteEntityById(id);
    }
}
