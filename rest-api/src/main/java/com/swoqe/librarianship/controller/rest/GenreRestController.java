package com.swoqe.librarianship.controller.rest;

import com.swoqe.librarianship.common.page.PageData;
import com.swoqe.librarianship.common.page.PageLink;
import com.swoqe.librarianship.dto.GenreDto;
import com.swoqe.librarianship.exception.SwoqeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/genres")
public class GenreRestController extends BaseRestController {

    @GetMapping()
    public PageData<GenreDto> findAllGenres(
            @RequestParam int pageSize,
            @RequestParam int page,
            @RequestParam(required = false) String textSearch,
            @RequestParam(required = false) String sortProperty,
            @RequestParam(required = false) String sortOrder
    ) throws SwoqeException {
        PageLink pageLink = createPageLink(pageSize, page, textSearch, sortProperty, sortOrder);
        return genreService.findAll(pageLink);
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

    @DeleteMapping("/delete/{genreId}")
    @PreAuthorize("hasAnyAuthority('BOOK_ADMIN')")
    public void deleteGenre(@PathVariable String genreId) throws SwoqeException {
        checkNotBlank("genreId", genreId);
        UUID uuid = toUUID(genreId);
        genreService.deleteById(uuid);
    }


}
