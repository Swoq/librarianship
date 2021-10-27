package com.swoqe.librarianship.service.impl;

import com.swoqe.librarianship.common.page.DaoUtil;
import com.swoqe.librarianship.common.page.PageData;
import com.swoqe.librarianship.common.page.PageLink;
import com.swoqe.librarianship.dto.GenreDto;
import com.swoqe.librarianship.dto.converter.GenreConverter;
import com.swoqe.librarianship.model.entity.GenreEntity;
import com.swoqe.librarianship.repository.GenreJpaRepository;
import com.swoqe.librarianship.service.AbstractJpaService;
import com.swoqe.librarianship.service.GenreService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
public class GenreServiceImpl extends AbstractJpaService<GenreDto, GenreEntity, GenreJpaRepository> implements GenreService {

    private final GenreConverter genreConverter;

    public GenreServiceImpl(GenreJpaRepository repository, GenreConverter genreConverter) {
        super(repository);
        this.genreConverter = genreConverter;
    }

    @Override
    public Set<GenreDto> findAll() {
        return genreConverter.createFromEntities(findAllEntities());
    }

    @Override
    public GenreDto findById(UUID id) {
        return genreConverter.toDto(findEntityById(id));
    }

    @Override
    public GenreDto save(GenreDto dto) {
        GenreEntity entity = genreConverter.toEntity(dto);
        GenreEntity savedEntity = saveEntity(entity);
        return genreConverter.toDto(savedEntity);
    }

    @Override
    public void delete(GenreDto dto) {
        GenreEntity entity = genreConverter.toEntity(dto);
        deleteEntity(entity);
    }

    @Override
    public void deleteById(UUID id) {
        deleteEntityById(id);
    }

    @Override
    public PageData<GenreDto> findAll(PageLink pageLink) {
        Page<GenreEntity> page = repository.findByGenreNameContains(
                Objects.toString(pageLink.getTextSearch(), ""),
                DaoUtil.toPageable(pageLink, Collections.emptyMap())
        );
        Set<GenreDto> data = genreConverter.createFromEntitiesSaveOrder(page.getContent());
        return new PageData<>(data, page.getTotalPages(), page.getTotalElements(), page.hasNext());
    }
}
