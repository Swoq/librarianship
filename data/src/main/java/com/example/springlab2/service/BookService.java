package com.example.springlab2.service;

import com.example.springlab2.common.page.PageData;
import com.example.springlab2.common.page.PageLink;
import com.example.springlab2.dto.BookDto;
import com.example.springlab2.exception.SwoqeException;

import java.util.UUID;

public interface BookService extends CrudService<BookDto> {

    PageData<BookDto> findAll(PageLink pageLink);
    PageData<BookDto> findAllByAuthorId(UUID authorId, PageLink pageLink);
}
