package com.example.springlab2.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends UUIDBased {


    public UserDto() {
        super();
    }
}
