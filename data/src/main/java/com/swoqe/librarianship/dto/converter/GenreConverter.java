package com.swoqe.librarianship.dto.converter;

import com.swoqe.librarianship.dto.GenreDto;
import com.swoqe.librarianship.model.entity.GenreEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
