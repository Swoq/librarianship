package com.swoqe.librarianship.controller.rest;

import com.swoqe.librarianship.dto.GenreDto;
import com.swoqe.librarianship.exception.SwoqeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/genres")

public class GenreRestController extends BaseRestController {

    @GetMapping()
    public Set<GenreDto> findAllGenres(){
        return genreService.findAll();
    }

    @GetMapping("/find/{genreId}")
    @PreAuthorize("hasAnyAuthority('COMMON_USER', 'BOOK_ADMIN')")
    public GenreDto findGenreById(@PathVariable String genreId) throws SwoqeException {
        checkNotBlank("genreId", genreId);
        UUID uuid = toUUID(genreId);
        return genreService.findById(uuid);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('BOOK_ADMIN')")
    public GenreDto saveOrUpdateGenre(@RequestBody GenreDto genreDto) {
        return genreService.save(genreDto);
    }

    @PostMapping("/delete/{genreId}")
    @PreAuthorize("hasAnyAuthority('BOOK_ADMIN')")
    public void deleteGenre(@PathVariable String genreId) throws SwoqeException {
        checkNotBlank("genreId", genreId);
        UUID uuid = toUUID(genreId);
        genreService.deleteById(uuid);
    }


}
