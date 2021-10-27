package com.swoqe.librarianship.ui.service.genre;

import com.swoqe.librarianship.dto.GenreDto;
import com.swoqe.librarianship.ui.service.AbstractRestService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GenreRestServiceImpl extends AbstractRestService<GenreDto> implements GenreRestService {

    public GenreRestServiceImpl(WebClient webClient) {
        super(webClient);
    }

    @Override
    public String getUri() {
        return "/api/genres";
    }
}
