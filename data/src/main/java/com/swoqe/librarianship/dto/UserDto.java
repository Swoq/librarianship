package com.swoqe.librarianship.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends UUIDBased {


    public UserDto() {
        super();
    }
}
