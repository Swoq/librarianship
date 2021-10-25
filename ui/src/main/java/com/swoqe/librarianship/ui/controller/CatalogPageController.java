package com.swoqe.librarianship.ui.controller;

import com.swoqe.librarianship.common.page.PageData;
import com.swoqe.librarianship.dto.BookDto;
import com.swoqe.librarianship.ui.service.book.BookRestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class CatalogPageController extends BaseController {

    private final BookRestService bookRestService;

    @GetMapping("/catalog")
    private PageData<BookDto> getCatalogPage(
            @RequestParam int pageSize,
            @RequestParam int page,
            @RequestParam(required = false) Optional<UUID> authorId,
            @RequestParam(required = false) String textSearch,
            @RequestParam(required = false) String sortProperty,
            @RequestParam(required = false) String sortOrder
    ) {
        PageData<BookDto> pageSync = bookRestService.getPageSync(pageSize, page);
        System.out.println(pageSync);
        return pageSync;
    }
}
