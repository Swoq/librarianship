package com.swoqe.librarianship.service;

import com.swoqe.librarianship.common.page.PageData;
import com.swoqe.librarianship.common.page.PageLink;
import com.swoqe.librarianship.dto.BookDto;
import com.swoqe.librarianship.dto.GenreDto;

public interface GenreService extends CrudService<GenreDto> {
    PageData<GenreDto> findAll(PageLink pageLink);
}
