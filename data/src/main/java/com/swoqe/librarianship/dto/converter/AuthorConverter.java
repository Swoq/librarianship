package com.swoqe.librarianship.dto.converter;

import com.swoqe.librarianship.dto.AuthorDto;
import com.swoqe.librarianship.model.entity.AuthorEntity;
import com.swoqe.librarianship.repository.BookJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter extends GenericConverter<AuthorDto, AuthorEntity> {

    private final BookJpaRepository booksRepository;

    public AuthorConverter(ModelMapper modelMapper, BookJpaRepository repository) {
        super(modelMapper);
        this.booksRepository = repository;
    }

    @Override
    public AuthorEntity toEntity(AuthorDto dto) {
        AuthorEntity entity = modelMapper.map(dto, AuthorEntity.class);
        return entity;
    }

    @Override
    public AuthorDto toDto(AuthorEntity entity) {
        AuthorDto dto = modelMapper.map(entity, AuthorDto.class);
        return dto;
    }
}
