package com.swoqe.librarianship.config.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserView {

    private UUID id;
    private String username;

}
