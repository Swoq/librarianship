package com.swoqe.librarianship.model.entity;

import com.swoqe.librarianship.model.ModelConstants;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = ModelConstants.AUTHOR_TABLE_NAME)
@EqualsAndHashCode(callSuper = true, exclude = {"books"})
@Data
@ToString(exclude = {"books"})
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntity extends PersonEntity {

    @ManyToMany(mappedBy = "authors")
    private Set<BookEntity> books;
}
