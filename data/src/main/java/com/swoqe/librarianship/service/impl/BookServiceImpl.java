package com.swoqe.librarianship.service.impl;

import com.swoqe.librarianship.common.page.DaoUtil;
import com.swoqe.librarianship.common.page.PageData;
import com.swoqe.librarianship.common.page.PageLink;
import com.swoqe.librarianship.dto.BookDto;
import com.swoqe.librarianship.dto.converter.BookConverter;
import com.swoqe.librarianship.exception.SwoqeErrorCode;
import com.swoqe.librarianship.exception.SwoqeException;
import com.swoqe.librarianship.model.entity.AuthorEntity;
import com.swoqe.librarianship.model.entity.BookEntity;
import com.swoqe.librarianship.repository.AuthorJpaRepository;
import com.swoqe.librarianship.repository.BookJpaRepository;
import com.swoqe.librarianship.service.AbstractJpaService;
import com.swoqe.librarianship.service.BookService;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl extends AbstractJpaService<BookDto, BookEntity, BookJpaRepository> implements BookService {

    private final BookConverter bookConverter;
    private final AuthorJpaRepository authorJpaRepository;

    public BookServiceImpl(BookJpaRepository repository, BookConverter bookConverter, AuthorJpaRepository authorJpaRepository) {
        super(repository);
        this.bookConverter = bookConverter;
        this.authorJpaRepository = authorJpaRepository;
    }

    @Override
    public Set<BookDto> findAll() {
        return bookConverter.createFromEntities(findAllEntities());
    }

    @Override
    public BookDto findById(UUID id) {
        return bookConverter.toDto(findEntityById(id));
    }

    @Override
    public BookDto save(BookDto object) {
        BookEntity entity = bookConverter.toEntity(object);
        BookEntity savedEntity = saveEntity(entity);
        return bookConverter.toDto(savedEntity);
    }

    @Override
    public void delete(BookDto object) {
        BookEntity entity = bookConverter.toEntity(object);
        deleteEntity(entity);
    }

    @Override
    public void deleteById(UUID id) {
        deleteEntityById(id);
    }

    @Override
    public PageData<BookDto> findAll(PageLink pageLink) {
        Page<BookEntity> page = repository.findByTitle(
                Objects.toString(pageLink.getTextSearch(), ""),
                DaoUtil.toPageable(pageLink, Collections.emptyMap())
        );
        Set<BookDto> data = bookConverter.createFromEntitiesSaveOrder(page.getContent());
        return new PageData<>(data, page.getTotalPages(), page.getTotalElements(), page.hasNext());
    }

    @Override
    @SneakyThrows
    public PageData<BookDto> findAllByAuthorId(UUID authorId, PageLink pageLink) {
        AuthorEntity entity = authorJpaRepository.findById(authorId)
                .orElseThrow(() -> new SwoqeException("Author '" + authorId + "' doesn't not exist", SwoqeErrorCode.BAD_REQUEST_PARAMS));
        Page<BookEntity> page = repository.findByTitleAndAuthor(
                entity,
                Objects.toString(pageLink.getTextSearch(), ""),
                DaoUtil.toPageable(pageLink, Collections.emptyMap())
        );
        Set<BookDto> data = bookConverter.createFromEntitiesSaveOrder(page.getContent());
        return new PageData<>(data, page.getTotalPages(), page.getTotalElements(), page.hasNext());
    }


}
