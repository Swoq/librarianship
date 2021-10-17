package com.swoqe.librarianship.service;

import com.swoqe.librarianship.common.page.PageData;
import com.swoqe.librarianship.common.page.PageLink;
import com.swoqe.librarianship.dto.AuthorDto;
import com.swoqe.librarianship.dto.BookDto;

import java.util.UUID;

public interface AuthorService extends CrudService<AuthorDto> {
    PageData<AuthorDto> findAll(PageLink pageLink);
    PageData<AuthorDto> findAllByBookId(UUID id, PageLink pageLink);
}
