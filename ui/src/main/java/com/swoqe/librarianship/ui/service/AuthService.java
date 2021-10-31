package com.swoqe.librarianship.ui.service;

import com.swoqe.librarianship.dto.security.AuthRequest;
import com.swoqe.librarianship.dto.security.AuthResponse;
import com.swoqe.librarianship.dto.security.CreateUserRequest;
import com.swoqe.librarianship.dto.security.UserView;

public interface AuthService {
    AuthResponse performAuthentication(AuthRequest authRequest);

    UserView performRegistration(CreateUserRequest createUserRequest);
}
