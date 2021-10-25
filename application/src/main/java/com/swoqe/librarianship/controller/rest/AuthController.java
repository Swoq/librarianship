package com.swoqe.librarianship.controller.rest;

import com.swoqe.librarianship.dto.security.AuthRequest;
import com.swoqe.librarianship.dto.security.AuthResponse;
import com.swoqe.librarianship.dto.security.CreateUserRequest;
import com.swoqe.librarianship.dto.security.UserView;
import com.swoqe.librarianship.security.SecurityUser;
import com.swoqe.librarianship.security.SecurityUserService;
import com.swoqe.librarianship.security.jwt.JwtTokenUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Tag(name = "Authentication")
@RestController @RequestMapping(path = "api/")
@RequiredArgsConstructor
public class AuthController extends BaseRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final SecurityUserService securityUserService;

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest request, HttpServletResponse response) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            SecurityUser user = (SecurityUser) authenticate.getPrincipal();

            String token = jwtTokenUtil.generateAccessToken(user);
            AuthResponse authResponse = new AuthResponse(token, user.getId());

            response.addCookie(new Cookie(HttpHeaders.AUTHORIZATION, token));
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(authResponse);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public UserView register(@RequestBody @Valid CreateUserRequest request) {
        return securityUserService.create(request);
    }

}