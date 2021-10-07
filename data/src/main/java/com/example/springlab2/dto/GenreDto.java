package com.example.springlab2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class GenreDto extends UUIDBased {

    private String genreName;

    private String description;

    public GenreDto() {
        super();
    }

}
