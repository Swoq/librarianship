package com.swoqe.librarianship.dto.security;

import lombok.Data;

import java.util.Set;

@Data
public class UpdateUserRequest {

    private Set<String> authorities;

}
