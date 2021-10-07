package com.example.springlab2.model.entity;

import com.example.springlab2.model.BaseSqlEntity;
import com.example.springlab2.model.ModelConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = ModelConstants.GENRE_TABLE_NAME)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreEntity extends BaseSqlEntity {

    @Column(name = ModelConstants.GENRE_NAME_COLUMN)
    private String genreName;

    @Column(name = ModelConstants.GENRE_DESCRIPTION_COLUMN, columnDefinition = "text")
    private String description;

    @ManyToMany(mappedBy = "genres")
    private Set<BookEntity> books = new HashSet<>();

}
