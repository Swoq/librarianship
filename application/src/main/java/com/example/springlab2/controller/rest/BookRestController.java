package com.example.springlab2.controller.rest;

import com.example.springlab2.common.page.PageData;
import com.example.springlab2.common.page.PageLink;
import com.example.springlab2.dto.BookDto;
import com.example.springlab2.exception.SwoqeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookRestController extends BaseRestController {
//
//    @GetMapping()
//    public Set<BookDto> findAllBooks() {
//        return bookService.findAll();
//    }

    @GetMapping()
    public PageData<BookDto> findAllBooksByParams(
            @RequestParam int pageSize,
            @RequestParam int page,
            @RequestParam(required = false) Optional<UUID> authorId,
            @RequestParam(required = false) String textSearch,
            @RequestParam(required = false) String sortProperty,
            @RequestParam(required = false) String sortOrder
    ) throws SwoqeException {
        PageLink pageLink = createPageLink(pageSize, page, textSearch, sortProperty, sortOrder);
        return authorId.map(id -> bookService.findAllByAuthorId(id, pageLink)).orElse(bookService.findAll(pageLink));
    }

    @GetMapping("/find/{bookId}")
    public BookDto findBookById(@PathVariable String bookId) throws SwoqeException {
        checkNotBlank("publicationId", bookId);
        UUID uuid = toUUID(bookId);
        return bookService.findById(uuid);
    }

    @PostMapping("/create")
    public BookDto saveOrUpdateBook(@RequestBody BookDto bookDto) {
        return bookService.save(bookDto);
    }

    @PostMapping("/delete/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable String bookId) throws SwoqeException {
        checkNotBlank("publicationId", bookId);
        UUID uuid = toUUID(bookId);
        bookService.deleteById(uuid);
    }
}
