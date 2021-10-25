package com.swoqe.librarianship.ui.controller;

import com.swoqe.librarianship.dto.security.AuthRequest;
import com.swoqe.librarianship.dto.security.AuthResponse;
import com.swoqe.librarianship.ui.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
public class AuthPageController {

    private final AuthService authService;
    private final AuthResponse authentication;

    @GetMapping("/login")
    public String getAuthPage(Model model) {
        model.addAttribute("loginRequest", new AuthRequest());
        return "login";
    }

    @PostMapping("/login/perform")
    public String performLogin(@ModelAttribute AuthRequest authRequest, HttpServletResponse response) {
        AuthResponse authResponse = authService.performAuthentication(authRequest);
        response.addCookie(new Cookie(HttpHeaders.AUTHORIZATION, authResponse.getToken()));
        authentication.setToken(authResponse.getToken());
        authentication.setUserId(authResponse.getUserId());
        return "redirect:/";
    }
}
