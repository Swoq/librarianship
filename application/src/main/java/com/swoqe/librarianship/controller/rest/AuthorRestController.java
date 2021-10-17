package com.swoqe.librarianship.controller.rest;

import com.swoqe.librarianship.common.page.PageData;
import com.swoqe.librarianship.common.page.PageLink;
import com.swoqe.librarianship.dto.AuthorDto;
import com.swoqe.librarianship.exception.SwoqeException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController extends BaseRestController {

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('COMMON_USER', 'BOOK_ADMIN')")
    public PageData<AuthorDto> findAllAuthorsByParams(
            @RequestParam int pageSize,
            @RequestParam int page,
            @RequestParam(required = false) Optional<UUID> bookId,
            @RequestParam(required = false) String textSearch,
            @RequestParam(required = false) String sortProperty,
            @RequestParam(required = false) String sortOrder
    ) throws SwoqeException {
        PageLink pageLink = createPageLink(pageSize, page, textSearch, sortProperty, sortOrder);
        return bookId.map(id -> authorService.findAllByBookId(id, pageLink))
                .orElse(authorService.findAll(pageLink));
    }

    @GetMapping("/find/{authorId}")
    @PreAuthorize("hasAnyAuthority('COMMON_USER', 'BOOK_ADMIN')")
    public AuthorDto findAuthorById(@PathVariable String authorId) throws SwoqeException {
        checkNotBlank("authorId", authorId);
        UUID uuid = toUUID(authorId);
        return authorService.findById(uuid);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('BOOK_ADMIN')")
    public AuthorDto saveOrUpdateBook(@RequestBody AuthorDto authorDto) {
        return authorService.save(authorDto);
    }

    @PostMapping("/delete/{authorId}")
    @PreAuthorize("hasAnyAuthority('BOOK_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable String authorId) throws SwoqeException {
        checkNotBlank("authorId", authorId);
        UUID uuid = toUUID(authorId);
        authorService.deleteById(uuid);
    }
}
