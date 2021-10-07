package com.example.springlab2.dto.converter;

import com.example.springlab2.dto.AuthorDto;
import com.example.springlab2.model.BaseSqlEntity;
import com.example.springlab2.model.entity.AuthorEntity;
import com.example.springlab2.model.entity.BookEntity;
import com.example.springlab2.repository.BookJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
