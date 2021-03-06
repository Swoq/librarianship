package com.swoqe.librarianship.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class PersonDto extends UUIDBased {
    private String firstName;
    private String lastName;
    private String email;
}
