package com.example.springlab2.service;

import com.example.springlab2.dto.UUIDBased;
import com.example.springlab2.model.BaseSqlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class AbstractJpaService<E extends UUIDBased, T extends BaseSqlEntity, R extends JpaRepository<T, UUID>> {

    protected R repository;

    public AbstractJpaService(R repository) {
        this.repository = repository;
    }

    protected final Set<T> findAllEntities() {
        return new HashSet<>(repository.findAll());
    }

    protected final T findEntityById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Object id:"+ id + " not found"));
    }

    @Transactional
    protected T saveEntity(T object) {
        if (object.getId() == null) {
            object.setId(UUID.randomUUID());
        }
        if (object.getCreatedTime() == 0) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            object.setCreatedTime(timestamp.getTime());
        }
        return repository.save(object);
    }

    @Transactional
    protected void deleteEntity(T object) {
        if (object.getId() == null || !repository.existsById(object.getId())) {
            throw new IllegalArgumentException("Object not found");
        }
        repository.delete(object);
    }

    @Transactional
    protected void deleteEntityById(UUID id) {
        if (id == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Object not found");
        }
        repository.deleteById(id);
    }


}
