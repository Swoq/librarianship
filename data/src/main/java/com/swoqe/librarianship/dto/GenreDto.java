package com.swoqe.librarianship.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GenreDto extends UUIDBased {

    private String genreName;

    private String description;

    public GenreDto() {
        super();
    }

}
