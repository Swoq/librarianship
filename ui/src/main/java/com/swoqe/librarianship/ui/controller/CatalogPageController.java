package com.swoqe.librarianship.ui.controller;

import com.swoqe.librarianship.common.page.PageData;
import com.swoqe.librarianship.dto.BookDto;
import com.swoqe.librarianship.dto.GenreDto;
import com.swoqe.librarianship.ui.service.book.BookRestService;
import com.swoqe.librarianship.ui.service.genre.GenreRestService;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class CatalogPageController extends BaseController {

    private final BookRestService bookRestService;
    private final GenreRestService genreRestService;

    @GetMapping("/catalog")
    private String getCatalogPage(
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) Optional<UUID> authorId,
            @RequestParam(required = false) String textSearch,
            @RequestParam(required = false) String sortProperty,
            @RequestParam(required = false) String sortOrder,
            Model model
    ) {
        PageData<BookDto> bookPage = bookRestService.getPageSync(new ParameterizedTypeReference<>(){}, pageSize, page);
        PageData<GenreDto> genrePage = genreRestService.getPageSync(new ParameterizedTypeReference<>(){}, pageSize, page);

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("genres", genrePage.getData());
        model.addAttribute("publications", bookPage.getData());

        return "catalog";
    }
}
