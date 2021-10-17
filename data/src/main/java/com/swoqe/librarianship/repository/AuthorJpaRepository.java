package com.swoqe.librarianship.repository;

import com.swoqe.librarianship.model.entity.AuthorEntity;
import com.swoqe.librarianship.model.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorJpaRepository extends JpaRepository<AuthorEntity, UUID> {
    @Query("SELECT a FROM AuthorEntity a " +
            "WHERE LOWER(CONCAT(a.firstName, a.lastName)) " +
            "LIKE LOWER(CONCAT('%', :textSearch, '%'))")
    Page<AuthorEntity> findByFirstAndLastNames(@Param("textSearch") String textSearch, Pageable pageable);


    @Query("SELECT DISTINCT a FROM AuthorEntity a " +
            "JOIN a.books b " +
            "WHERE b = :book " +
            "AND LOWER(CONCAT(a.firstName, a.lastName)) " +
            "LIKE LOWER(CONCAT('%', :textSearch, '%'))")
    Page<AuthorEntity> findByFirstAndLastNamesAndBook(@Param("author") BookEntity book,
                                                      @Param("textSearch") String textSearch,
                                                      Pageable pageable);
}
