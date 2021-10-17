package com.swoqe.librarianship.service.impl;

import com.swoqe.librarianship.common.page.DaoUtil;
import com.swoqe.librarianship.common.page.PageData;
import com.swoqe.librarianship.common.page.PageLink;
import com.swoqe.librarianship.dto.AuthorDto;
import com.swoqe.librarianship.dto.BookDto;
import com.swoqe.librarianship.dto.converter.AuthorConverter;
import com.swoqe.librarianship.exception.SwoqeErrorCode;
import com.swoqe.librarianship.exception.SwoqeException;
import com.swoqe.librarianship.model.entity.AuthorEntity;
import com.swoqe.librarianship.model.entity.BookEntity;
import com.swoqe.librarianship.repository.AuthorJpaRepository;
import com.swoqe.librarianship.repository.BookJpaRepository;
import com.swoqe.librarianship.service.AbstractJpaService;
import com.swoqe.librarianship.service.AuthorService;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthorServiceImpl extends AbstractJpaService<AuthorDto, AuthorEntity, AuthorJpaRepository> implements AuthorService {

    private final AuthorConverter authorConverter;
    private final BookJpaRepository bookJpaRepository;

    public AuthorServiceImpl(AuthorJpaRepository repository, AuthorConverter authorConverter, BookJpaRepository bookJpaRepository) {
        super(repository);
        this.authorConverter = authorConverter;
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public Set<AuthorDto> findAll() {
        return null;
    }

    @Override
    public AuthorDto findById(UUID id) {
        return null;
    }

    @Override
    public AuthorDto save(AuthorDto object) {
        return null;
    }

    @Override
    public void delete(AuthorDto object) {

    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public PageData<AuthorDto> findAll(PageLink pageLink) {
        Page<AuthorEntity> page = repository.findByFirstAndLastNames(
                Objects.toString(pageLink.getTextSearch(), ""),
                DaoUtil.toPageable(pageLink, Collections.emptyMap())
        );
        Set<AuthorDto> data = authorConverter.createFromEntitiesSaveOrder(page.getContent());
        return new PageData<>(data, page.getTotalPages(), page.getTotalElements(), page.hasNext());
    }

    @Override
    @SneakyThrows
    public PageData<AuthorDto> findAllByBookId(UUID id, PageLink pageLink) {
        BookEntity entity = bookJpaRepository.findById(id)
                .orElseThrow(() -> new SwoqeException("Book '" + id + "' doesn't not exist", SwoqeErrorCode.BAD_REQUEST_PARAMS));
        Page<AuthorEntity> page = repository.findByFirstAndLastNamesAndBook(
                entity,
                Objects.toString(pageLink.getTextSearch(), ""),
                DaoUtil.toPageable(pageLink, Collections.emptyMap())
        );
        Set<AuthorDto> data = authorConverter.createFromEntitiesSaveOrder(page.getContent());
        return new PageData<>(data, page.getTotalPages(), page.getTotalElements(), page.hasNext());
    }
}
