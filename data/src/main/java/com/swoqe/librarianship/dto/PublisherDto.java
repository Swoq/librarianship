package com.swoqe.librarianship.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PublisherDto extends UUIDBased {

    private String name;
    private String email;
    private String address;

    public PublisherDto() {
        super();
    }
}
