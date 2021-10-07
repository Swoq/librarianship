package com.example.springlab2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true, exclude = {"publisherDto", "genresDtos"})
@ToString(exclude = {"publisherDto", "genresDtos"})
public class BookDto extends UUIDBased {

    private String title;
    private String description;
    private LocalDate publicationDate;
    private String imageUrl;
    private PublisherDto publisherDto;
    private Set<GenreDto> genresDtos;
    private Set<AuthorDto> authorDtos;

    public BookDto() {
        super();
    }
}
