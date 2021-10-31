package com.swoqe.librarianship.dto.security;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequest {
    @NotBlank(message = "Email is mandatory")
    @Size(min = 1, max=255)
    @Email
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 1, max=255)
    private String password;

    @NotBlank
    @NotBlank(message = "You must repeat the password")
    @Size(min = 1, max=255)
    private String rePassword;

    @NotBlank(message = "First name is mandatory")
    @Size(min = 1, max=255)
    @Pattern(regexp="^[a-zA-Zа-яА-Я]+$", message = "Only letters are allowed")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Size(min = 1, max=255)
    @Pattern(regexp="^[a-zA-Zа-яА-Я]+$", message = "Only letters are allowed")
    private String lastName;

}
