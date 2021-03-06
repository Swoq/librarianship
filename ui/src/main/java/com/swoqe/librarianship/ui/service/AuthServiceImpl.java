package com.swoqe.librarianship.ui.service;

import com.swoqe.librarianship.dto.security.AuthRequest;
import com.swoqe.librarianship.dto.security.AuthResponse;
import com.swoqe.librarianship.dto.security.CreateUserRequest;
import com.swoqe.librarianship.dto.security.UserView;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final WebClient webClient;
    private final String AUTH_URI = "api/login";
    private final String REG_URI = "api/registration";

    @Override
    public final AuthResponse performAuthentication(AuthRequest authRequest) {
        return webClient
                .post()
                .uri(AUTH_URI)
                .body(BodyInserters.fromValue(authRequest))
                .retrieve()
                .bodyToMono(AuthResponse.class)
                .block();
    }

    @Override
    public final UserView performRegistration(CreateUserRequest createUserRequest) {
        return webClient
                .post()
                .uri(REG_URI)
                .body(BodyInserters.fromValue(createUserRequest))
                .retrieve()
                .bodyToMono(UserView.class)
                .block();
    }
}
