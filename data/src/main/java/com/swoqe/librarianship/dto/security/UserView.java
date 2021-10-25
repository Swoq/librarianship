package com.swoqe.librarianship.dto.security;

import lombok.Data;

import java.util.UUID;

@Data
public class UserView {

    private UUID id;
    private String username;

}
