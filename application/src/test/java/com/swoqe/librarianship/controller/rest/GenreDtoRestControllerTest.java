package com.swoqe.librarianship.controller.rest;

import com.swoqe.librarianship.dto.GenreDto;
import com.swoqe.librarianship.service.GenreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class GenreDtoRestControllerTest {

    ObjectMapper mapper = new ObjectMapper();

    MockMvc mockMvc;

    @MockBean
    GenreService genreService;

    @Autowired
    GenreRestController controller;

    Set<GenreDto> genreDtos;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        GenreDto e1 = new GenreDto();
        e1.setId(UUID.randomUUID());
        GenreDto e2 = new GenreDto();
        e2.setId(UUID.randomUUID());
        genreDtos = Set.of(e1, e2);
    }

    @Test
    void findAll() throws Exception {
        when(genreService.findAll()).thenReturn(genreDtos);
        mockMvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(mapper.writeValueAsString(genreDtos)));
    }

    @Test
    void findById() throws Exception {
        GenreDto genreDto = genreDtos.stream().findFirst().orElse(new GenreDto());
        genreDto.setId(UUID.randomUUID());
        when(genreService.findById(any())).thenReturn(genreDto);
        mockMvc.perform(get("/api/genres/find/" + genreDto.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(mapper.writeValueAsString(genreDto)));
    }
}