package com.swoqe.librarianship.ui.service.book;

import com.swoqe.librarianship.dto.BookDto;
import com.swoqe.librarianship.ui.service.AbstractRestService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BookRestServiceImpl extends AbstractRestService<BookDto> implements BookRestService {

    private String URI = "/api/books";

    public BookRestServiceImpl(WebClient webClient) {
        super(webClient);
    }


    @Override
    public String getUri() {
        return URI;
    }
}
