package com.example.springlab2.model.entity;

import com.example.springlab2.model.BaseSqlEntity;
import com.example.springlab2.model.ModelConstants;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = ModelConstants.BOOK_TABLE_NAME)
@EqualsAndHashCode(callSuper = true, exclude = {"publisher", "genres"})
@Data
@ToString(exclude = {"publisher", "genres"})
@AllArgsConstructor
public class BookEntity extends BaseSqlEntity {

    @Column(name = ModelConstants.BOOK_TITLE_COLUMN)
    private String title;

    @Column(name = ModelConstants.BOOK_DESCRIPTION_COLUMN)
    private String description;

    @Column(name = ModelConstants.BOOK_DATE_COLUMN)
    private LocalDate publicationDate;

    @Column(name = ModelConstants.IMAGE_URL_COLUMN)
    private String imageUrl;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = ModelConstants.BOOK_AUTHOR_TABLE_NAME,
            joinColumns = @JoinColumn(name = ModelConstants.BOOK_ID_COLUMN),
            inverseJoinColumns = @JoinColumn(name = ModelConstants.AUTHOR_ID_COLUMN)
    )
    private Set<AuthorEntity> authors;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ModelConstants.PUBLISHER_ENTITY_PROPERTY)
    private PublisherEntity publisher;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = ModelConstants.BOOK_GENRES_TABLE_NAME,
            joinColumns = @JoinColumn(name = ModelConstants.BOOK_ID_COLUMN),
            inverseJoinColumns = @JoinColumn(name = ModelConstants.GENRE_ID_COLUMN)
    )
    private Set<GenreEntity> genres;

    public BookEntity() {
        super();
    }
}
