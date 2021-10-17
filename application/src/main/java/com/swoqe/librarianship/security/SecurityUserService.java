package com.swoqe.librarianship.security;

import com.swoqe.librarianship.config.dto.CreateUserRequest;
import com.swoqe.librarianship.config.dto.UpdateUserRequest;
import com.swoqe.librarianship.config.dto.UserView;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {

    private final SecurityUserRepository securityUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserView create(CreateUserRequest request) {
        if (securityUserRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ValidationException("Username exists!");
        }
        if (!request.getPassword().equals(request.getRePassword())) {
            throw new ValidationException("Passwords don't match!");
        }

        SecurityUser user = toSecurityUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setId(UUID.randomUUID());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setCreatedTime(timestamp.getTime());
        user = securityUserRepository.save(user);

        return toUserView(user);
    }

    @Transactional
    public UserView update(UUID id, UpdateUserRequest request) {
        SecurityUser user = securityUserRepository.getById(id);
        updateSecurityUser(user, request);

        user = securityUserRepository.save(user);

        return toUserView(user);
    }

    @Transactional
    public UserView delete(UUID id) {
        SecurityUser user = securityUserRepository.getById(id);

        user.setUsername(user.getUsername().replace("@", String.format("_%s@", user.getId().toString())));
        user.setEnabled(false);
        user = securityUserRepository.save(user);

        return toUserView(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return securityUserRepository
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(format("User with username - %s, not found", username))
                );
    }

    public boolean usernameExists(String username) {
        return securityUserRepository.findByUsername(username).isPresent();
    }

    public UserView getUser(UUID id) {
        return toUserView(securityUserRepository.getById(id));
    }

    private SecurityUser toSecurityUser(CreateUserRequest request) {
        SecurityUser user = new SecurityUser();
        user.setAuthorities(Set.of(new CustomAuthority(AuthoritiesNames.COMMON_USER)));
        user.setUsername(request.getUsername());
        return user;
    }

    private UserView toUserView(SecurityUser user) {
        UserView userView = new UserView();
        userView.setId(user.getId());
        userView.setUsername(user.getUsername());
        return userView;
    }

    private void updateSecurityUser(SecurityUser user, UpdateUserRequest request) {
        Set<CustomAuthority> authorities = request.getAuthorities()
                .stream()
                .map(str -> new CustomAuthority(AuthoritiesNames.valueOf(str))).collect(Collectors.toSet());
        user.setAuthorities(authorities);
    }
}
