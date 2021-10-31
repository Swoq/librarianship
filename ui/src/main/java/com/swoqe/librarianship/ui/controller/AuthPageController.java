package com.swoqe.librarianship.ui.controller;

import com.swoqe.librarianship.dto.security.AuthRequest;
import com.swoqe.librarianship.dto.security.AuthResponse;
import com.swoqe.librarianship.dto.security.CreateUserRequest;
import com.swoqe.librarianship.dto.security.UserView;
import com.swoqe.librarianship.ui.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        model.addAttribute("request", createUserRequest);
        return "registration";
    }

    @PostMapping("/registration/perform")
    public String performRegistration(@ModelAttribute @Valid CreateUserRequest createUserRequest,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        UserView userView = authService.performRegistration(createUserRequest);
        return "redirect:/";
    }

    @PostMapping("/login/perform")
    public String performLogin(@ModelAttribute @Valid AuthRequest loginRequest,
                               BindingResult bindingResult,
                               HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        AuthResponse authResponse = authService.performAuthentication(loginRequest);
        response.addCookie(new Cookie(HttpHeaders.AUTHORIZATION, authResponse.getToken()));
        authentication.setToken(authResponse.getToken());
        authentication.setUserId(authResponse.getUserId());
        return "redirect:/";
    }
}
