package com.swoqe.librarianship.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CrudService<T> {

    Set<T> findAll();

    T findById(UUID id);

    T save(T object);

    void delete(T object);

    void deleteById(UUID id);
}