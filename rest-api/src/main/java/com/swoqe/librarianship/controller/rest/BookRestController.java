package com.swoqe.librarianship.controller.rest;

import com.swoqe.librarianship.common.page.PageData;
import com.swoqe.librarianship.common.page.PageLink;
import com.swoqe.librarianship.dto.BookDto;
import com.swoqe.librarianship.exception.SwoqeException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookRestController extends BaseRestController {

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('COMMON_USER', 'BOOK_ADMIN')")
    public PageData<BookDto> findAllBooksByParams(
            @RequestParam int pageSize,
            @RequestParam int page,
            @RequestParam(required = false) Optional<UUID> authorId,
            @RequestParam(required = false) String textSearch,
            @RequestParam(required = false) String sortProperty,
            @RequestParam(required = false) String sortOrder
    ) throws SwoqeException {
        PageLink pageLink = createPageLink(pageSize, page, textSearch, sortProperty, sortOrder);
        return authorId.map(id -> bookService.findAllByAuthorId(id, pageLink))
                .orElse(bookService.findAll(pageLink));
    }

    @GetMapping("/find/{bookId}")
    @PreAuthorize("hasAnyAuthority('COMMON_USER', 'BOOK_ADMIN')")
    public BookDto findBookById(@PathVariable String bookId) throws SwoqeException {
        checkNotBlank("bookId", bookId);
        UUID uuid = toUUID(bookId);
        return bookService.findById(uuid);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('BOOK_ADMIN')")
    public BookDto saveOrUpdateBook(@RequestBody BookDto bookDto) {
        return bookService.save(bookDto);
    }

    @DeleteMapping("/delete/{bookId}")
    @PreAuthorize("hasAnyAuthority('BOOK_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable String bookId) throws SwoqeException {
        checkNotBlank("bookId", bookId);
        UUID uuid = toUUID(bookId);
        bookService.deleteById(uuid);
    }
}
