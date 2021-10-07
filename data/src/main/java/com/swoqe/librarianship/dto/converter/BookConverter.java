package com.swoqe.librarianship.dto.converter;

import com.swoqe.librarianship.dto.AuthorDto;
import com.swoqe.librarianship.dto.GenreDto;
import com.swoqe.librarianship.dto.BookDto;
import com.swoqe.librarianship.model.entity.AuthorEntity;
import com.swoqe.librarianship.model.entity.GenreEntity;
import com.swoqe.librarianship.model.entity.BookEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BookConverter extends GenericConverter<BookDto, BookEntity> {

    private final GenreConverter genreConverter;
    private final AuthorConverter authorConverter;

    public BookConverter(ModelMapper modelMapper, GenreConverter genreConverter, AuthorConverter authorConverter) {
        super(modelMapper);
        this.genreConverter = genreConverter;
        this.authorConverter = authorConverter;
    }

    @Override
    public BookEntity toEntity(BookDto dto) {
        if (dto == null) {
            return null;
        }
        BookEntity map = modelMapper.map(dto, BookEntity.class);
        Set<GenreEntity> genreEntities = genreConverter.createFromDtos(dto.getGenresDtos());
        Set<AuthorEntity> authorEntities = authorConverter.createFromDtos(dto.getAuthorDtos());
        map.setGenres(genreEntities);
        map.setAuthors(authorEntities);
        return map;
    }

    @Override
    public BookDto toDto(BookEntity entity) {
        if (entity == null) {
            return null;
        }
        BookDto map = modelMapper.map(entity, BookDto.class);
        Set<GenreDto> genreDtos = genreConverter.createFromEntities(entity.getGenres());
        Set<AuthorDto> authorDtos = authorConverter.createFromEntities(entity.getAuthors());

        map.setGenresDtos(genreDtos);
        map.setAuthorDtos(authorDtos);
        return map;
    }
}
