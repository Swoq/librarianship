package com.swoqe.librarianship.ui.config;

import com.swoqe.librarianship.dto.security.AuthResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class AuthenticationConfiguration {

    @Bean
    @Scope(
            value = WebApplicationContext.SCOPE_SESSION,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AuthResponse authentication() {
        return new AuthResponse();
    }
}
