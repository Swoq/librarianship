package com.example.springlab2.dto.converter;

import com.example.springlab2.dto.GenreDto;
import com.example.springlab2.model.BaseSqlEntity;
import com.example.springlab2.model.entity.GenreEntity;
import com.example.springlab2.model.entity.BookEntity;
import com.example.springlab2.repository.BookJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class GenreConverter extends GenericConverter<GenreDto, GenreEntity> {

    public GenreConverter(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public GenreDto toDto(GenreEntity entity) {
        if (entity == null) {
            return null;
        }

        GenreDto map = modelMapper.map(entity, GenreDto.class);
        return map;
    }

    @Override
    public GenreEntity toEntity(GenreDto dto) {
        if (dto == null) {
            return null;
        }
        GenreEntity map = modelMapper.map(dto, GenreEntity.class);
//        Set<BookEntity> books = dto.getBooksIds()
//                .stream()
//                .map(id -> booksRepository.findById(id).orElse(null))
//                .filter(Objects::nonNull)
//                .collect(Collectors.toSet());
//        map.setBooks(books);
        return map;
    }
}
