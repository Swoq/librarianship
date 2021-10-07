package com.example.springlab2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

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
