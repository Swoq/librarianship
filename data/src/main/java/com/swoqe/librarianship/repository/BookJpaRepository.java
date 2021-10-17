package com.swoqe.librarianship.repository;

import com.swoqe.librarianship.model.entity.AuthorEntity;
import com.swoqe.librarianship.model.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BookJpaRepository extends JpaRepository<BookEntity, UUID> {

    @Query("SELECT b FROM BookEntity b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :textSearch, '%'))")
    Page<BookEntity> findByTitle(@Param("textSearch") String textSearch, Pageable pageable);


    @Query("SELECT DISTINCT b FROM BookEntity b " +
            "JOIN b.authors a " +
            "WHERE a = :author " +
            "AND (b.title) LIKE LOWER(CONCAT('%', :textSearch, '%'))")
    Page<BookEntity> findByTitleAndAuthor(@Param("author") AuthorEntity author,
                                          @Param("textSearch") String textSearch,
                                          Pageable pageable);
}
