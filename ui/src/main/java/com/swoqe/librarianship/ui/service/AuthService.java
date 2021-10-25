package com.swoqe.librarianship.ui.service;

import com.swoqe.librarianship.dto.security.AuthRequest;
import com.swoqe.librarianship.dto.security.AuthResponse;

public interface AuthService {
    AuthResponse performAuthentication(AuthRequest authRequest);
}
