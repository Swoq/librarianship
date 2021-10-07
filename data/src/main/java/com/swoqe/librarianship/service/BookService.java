package com.swoqe.librarianship.service;

import com.swoqe.librarianship.common.page.PageData;
import com.swoqe.librarianship.common.page.PageLink;
import com.swoqe.librarianship.dto.BookDto;

import java.util.UUID;

public interface BookService extends CrudService<BookDto> {

    PageData<BookDto> findAll(PageLink pageLink);
    PageData<BookDto> findAllByAuthorId(UUID authorId, PageLink pageLink);
}
