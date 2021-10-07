package com.example.springlab2.dto.converter;

import com.example.springlab2.dto.UUIDBased;
import com.example.springlab2.model.BaseSqlEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class GenericConverter<D extends UUIDBased, E extends BaseSqlEntity> {

    protected final ModelMapper modelMapper;

    public abstract E toEntity(D dto);

    public abstract D toDto(E entity);

    public final Set<D> createFromEntities(final Collection<E> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    public final Set<D> createFromEntitiesSaveOrder(final Collection<E> entities) {
        Set<D> dtos = new LinkedHashSet<>();
        for (E entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public final Set<E> createFromDtosSaveOrder(final Collection<D> dtos) {
        Set<E> entities = new LinkedHashSet<>();
        for (D dto : dtos) {
            entities.add(toEntity(dto));
        }
        return entities;
    }

    public final Set<E> createFromDtos(final Collection<D> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

}