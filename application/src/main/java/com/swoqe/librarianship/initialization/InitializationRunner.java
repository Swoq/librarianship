package com.swoqe.librarianship.initialization;

import com.swoqe.librarianship.security.AuthoritiesNames;
import com.swoqe.librarianship.security.CustomAuthority;
import com.swoqe.librarianship.security.CustomAuthorityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(value="initialization_mode", havingValue = "true")
@AllArgsConstructor
@Slf4j
public class InitializationRunner implements CommandLineRunner {

    private final CustomAuthorityRepository authorityRepository;

    @Override
    public void run(String... args) {
        List<CustomAuthority> authorities = Arrays.stream(AuthoritiesNames.values())
                .map(CustomAuthority::new)
                .collect(Collectors.toList());
        authorityRepository.saveAllAndFlush(authorities);
        log.info("Initialization has been completed");
    }

}
